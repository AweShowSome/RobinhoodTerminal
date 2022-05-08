package shell

import httpclient.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json
import model.ApplicationConfig
import model.OrderSide
import robinhood.getDividends
import robinhood.getInstrument
import robinhood.getInvestmentProfile
import robinhood.getMarkets
import robinhood.getPortfolios
import robinhood.getPositions
import robinhood.getQuote
import robinhood.getUserInfo
import util.readInputSafely
import java.util.UUID

class RobinhoodShell(
    val applicationConfig: ApplicationConfig,
    val history: MutableList<String> = mutableListOf()
) {

    companion object {
        val Json = Json {
            encodeDefaults = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }
    }

    val client = HttpClient(OkHttp.create())
    val shellService = ShellService(applicationConfig, client)

    suspend fun shellLoop() {
        val session = if (shellService.needsLogin()) {
            shellService.login()
        } else {
            applicationConfig.session
        }

        println("Welcome Back")
        println("Select a task (use `h` for list of commands)")

        // 'break' terminates the while loop, which terminating the program.
        while (true) {
            val input = readInputSafely(">> ")
            when (input) {
                null -> println("press 'h' for help")
                "stop" -> {
                    break
                }
                "h" -> {
                    println("TL;DR: h, a (no longer works, thanks RH), b, d, instrument, investment_profile, m , portfolios, positions, q, s, u")
                }
                // currently unavailable
//                "a" -> {
//                    println(getApplications(client, loggedIn.accessToken))
//                }
                "b" -> {
                    println(shellService.makeOrder(OrderSide.buy, session, client, session.accessToken))
                }
                "d" -> {
                    println(getDividends(client, session.accessToken))
                }
                "instrument" -> {
                    println("Enter Instrument Id")
                    val id = UUID.fromString(readLine()) // assumes you know the id, this has no error handling
                    println(getInstrument(id, client, session.accessToken)) // could make id accept a string
                }
                "investment_profile" -> {
                    println(getInvestmentProfile(client, session.accessToken))
                }
                "m" -> {
                    println(getMarkets(client, session.accessToken))
                }
                "portfolios" -> {
                    println(getPortfolios(client, session.accessToken)) // "p" || "portfolio"
                }
                "positions" -> {
                    println(getPositions(client, session.accessToken))
                }
                "q" -> {
                    println("Enter stock ticker")
                    val id = readLine() ?: "no ticker" // assumes you know the ticker
                    println(getQuote(id.uppercase(), client, session.accessToken))
                }
                "s" -> {
                    println(shellService.makeOrder(OrderSide.sell, session, client, session.accessToken))
                }
                "u" -> {
                    println(getUserInfo(client, session.accessToken))
                }
                else -> {
                    println("not valid action")
                }
            }
            if (input != null) {
                history.add(input)
            }
            println(history) // remove if you dont want to view history
        }
    }

    suspend fun start() {
        shellLoop()
    }
}

// https://api.nasdaq.com/api/quote/watchlist?symbol=tsla|stocks use this api instead to retreive stock info if you want to spam call an api. This would still need to be set up.
