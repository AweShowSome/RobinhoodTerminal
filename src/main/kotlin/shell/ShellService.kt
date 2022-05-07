package shell

import httpclient.HttpClient
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.encodeToJsonElement
import model.*
import robinhood.*

class ShellService(private val applicationConfig: ApplicationConfig, private val client: HttpClient) {
    fun needsLogin(): Boolean {
        if (applicationConfig.session.accessToken.isBlank() ||
            applicationConfig.session.accessToken.isBlank() ||
            applicationConfig.session.expiresIn == 0 ||
            applicationConfig.session.account.contains("XXXXXXXX")
        ) {
            return true
        }
        return false
    }

    suspend fun login(): Session {
        // either get the credentials from application.conf or from console
        var login = obtainLoginCredentials(true)
        var payload = RobinhoodShell.Json.encodeToJsonElement(login)
        var loginResponse = client.post(ApiUrls.login, payload)
        var firstAttempt = true
        while (loginResponse.status != HttpStatusCode.OK) {
            if (!firstAttempt) println("Could not login, try again")
            login = obtainLoginCredentials(false)
            payload = RobinhoodShell.Json.encodeToJsonElement(login)
            loginResponse = client.post(ApiUrls.login, payload)
            firstAttempt = false
        }

        val loginResponseContent = loginResponse.readContent()

        // check for 2fa code
        val loggedInResponse = if (loginResponseContent.contains("\"mfa_required\":true")) {
            println("Enter 2FA code:")
            var code = readLine()?.trim()?.toInt() ?: -1

            val login2FA = Login(
                username = login.username,
                password = login.password,
                mfaCode = code
            )

            var mfaPayload = RobinhoodShell.Json.encodeToJsonElement(login2FA)
            var mfaResponse = client.post(ApiUrls.login, mfaPayload)
            var mfaResponseContent = mfaResponse.readContent()

            // need to try this to failure. Apparently, there is a limit, maybe 5 attempts?
            while (mfaResponse.status != HttpStatusCode.OK && mfaResponseContent.contains("Please enter a valid code.")) {
                println("Please enter a valid code: ")
                code = readLine()?.trim()?.toInt() ?: -1
                mfaPayload = RobinhoodShell.Json.encodeToJsonElement(login2FA.copy(mfaCode = code))
                mfaResponse = client.post(ApiUrls.login, mfaPayload)
                mfaResponseContent = mfaResponse.readContent()
            }
            mfaResponseContent
        } else {
            loginResponseContent
        }
        println()

        val loggedIn = RobinhoodShell.Json.decodeFromString<LoggedIn>(loggedInResponse)
        val account = getPortfolios(client, loggedIn.accessToken).results.first().account // TODO: configure and use account API instead of this
        return loggedIn.toSession(account)
    }

    private fun obtainLoginCredentials(tryUsingFromConfig: Boolean = false): Login {
        val username = if (tryUsingFromConfig) applicationConfig.credentials.username else readInputSafely("Username: ")
        val password = if (tryUsingFromConfig) applicationConfig.credentials.password else readInputSafely("Password: ", true)

        return Login(username, password)
    }

    suspend fun makeOrder(side: OrderSide, session: Session, client: HttpClient, bearerToken: String): OrdersResponse? {
        var symbol = readInputSafely("Stock Symbol: ").uppercase()
        var quote = getQuote(symbol, client, bearerToken)

        while (quote == null) {
            println("Bad symbol, try again")
            symbol = readInputSafely("Stock Symbol: ")
            quote = getQuote(symbol.uppercase(), client, bearerToken)
        }

        // original
//        var type = readInputSafely("Order type (market, limit): ").lowercase() // or could default to "market" order, also make an enum
//        while (type != "limit" && type != "market") {
//            type = readInputSafely("Bad order type, try again. Order type (market, limit): ")
//        }

        val type = readInputSafely("Order type (market, limit): ").let {
            var input = it.lowercase()
            while (input != "market" && input != "limit") {
                input = readInputSafely("Bad order type, try again. Order type (market, limit): ").lowercase()
            }
            input
        }

        // trying to use less vars
//        val typeee = try {
//            val input = readInputSafely("Order type (market, limit): ").lowercase()
//            if (input != "limit" && input != "market") {
//                throw IllegalArgumentException(input)
//            } else {
//                input
//            }
//        } catch (e: IllegalArgumentException) {
//            while ((e.message != "limit" && e.message != "market") &&) {
//                type = readInputSafely("Not an option, pick an order type (market, limit): ")
//            }
//        }

        // original
//        val price = if (type == "limit") {
//            println("Limit price: ")
//            readLine()?.toDouble() // if no limit price is set, then it will be a market order.
//        } else {
//            null
//        }

        // new
        val price =
            if (type == "limit") {
                var input = readInputSafely("Limit price: ")
                while (!input.canBeDouble()) {
                    input = readInputSafely("Not valid input for price, try again. Limit price: ")
                }
                input.toDouble()
            } else {
                null
            }

        // original
//        println("Quantity: ")
//        val quantity = readLine()?.toInt() ?: 0

        // original
//        println("Time duration Good for Day or Good Till Canceled (gfd, gtc): ")
//        val timeInForce = readLine() ?: "gtc"

        val timeInForce = readInputSafely("Time duration Good for Day or Good Till Canceled (gfd, gtc): ").let {
            var input = it.lowercase()
            while (input != "gfd" && input != "gtc") {
                input = readInputSafely("Bad time duration, try again. Good for Day or Good Till Canceled (gfd, gtc): ")
            }
            input
        }

        val quantity = readInputSafely("Quantity: ").let {
            var input = it
            if (type == "limit") {
                while (!input.canBeInt()) {
                    input = readInputSafely("Not valid input for quantity, try again (limit order cannot have fractional quantity). Quantity: ")
                }
            } else {
                while (!input.canBeDouble()) {
                    input = readInputSafely("Not valid input for quantity, try again. Quantity: ")
                }
            }
            input.toDouble()
        }


        quote = getQuote(symbol, client, bearerToken)

        if (quote == null) {
            println("quote is unexpectedly bad and returned null")
            return null
        }

        return makeOrder(session.account, quote.instrument, if (type == "market" || price == null) (quote.askPrice * 1.05).round(2) else price, quantity, side.name, symbol.uppercase(), timeInForce, type = type, client = client, bearerToken = bearerToken)
    }
}