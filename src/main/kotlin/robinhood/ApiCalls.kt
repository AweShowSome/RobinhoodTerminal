package robinhood

import httpclient.HttpClient
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToJsonElement
import model.Portfolios
import model.Positions
import model.Dividends
import model.Markets
import model.Applications
import model.User
import model.InvestmentProfile
import model.Instruments
import model.Quotes
import model.Orders
import model.OrdersResponse
import shell.RobinhoodShell
import java.util.UUID
import util.readContent

// GETs
suspend fun getPortfolios(client: HttpClient, bearerToken: String): Portfolios {
    val response = client.get(ApiUrls.portfolios, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}
suspend fun getPositions(client: HttpClient, bearerToken: String):Positions {
    val response = client.get(ApiUrls.positions, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

suspend fun getDividends(client: HttpClient, bearerToken: String): Dividends {
    val response = client.get(ApiUrls.dividends, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

suspend fun getMarkets(client: HttpClient, bearerToken: String): Markets {
    val response = client.get(ApiUrls.markets, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

// Currently does not work, maybe Robinhood stopped wanting to use this?
suspend fun getApplications(client: HttpClient, bearerToken: String): Applications {
    val response = client.get(ApiUrls.applications, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

suspend fun getUserInfo(client: HttpClient, bearerToken: String): User {
    val response = client.get(ApiUrls.user, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

suspend fun getInvestmentProfile(client: HttpClient, bearerToken: String): InvestmentProfile {
    val response = client.get(ApiUrls.investmentProfile, bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

suspend fun getInstrument(id: UUID, client: HttpClient, bearerToken: String): Instruments {
    val response = client.get(ApiUrls.instruments(id), bearerToken)
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

// could be better by taking in a list of tickers
// or could create a seperate function that takes in a list and calls this function, but wasted effort in passing around client and token
suspend fun getQuote(ticker: String, client: HttpClient, bearerToken: String): Quotes? {
    val response = client.get(ApiUrls.quotes(ticker.uppercase()), bearerToken)
    if (response.status != OK) {
        return null
    }
    return RobinhoodShell.Json.decodeFromStream(response.content.toInputStream())
}

// POSTs
// consider either calling it symbol or ticker, pick one
suspend fun makeOrder(account: String, instrument: String, price: Double, quantity: Double, side: String, symbol: String, timeInForce: String = "gfd", trigger: String = "immediate", type: String = "market", client: HttpClient, bearerToken: String): OrdersResponse {
    val order = Orders(
        account = account,
        instrument = instrument,
        price = price,
        quantity = quantity,
        side = side,
        symbol = symbol,
        timeInForce = timeInForce,
        trigger = trigger,
        type = type
    )
    val orderJson = RobinhoodShell.Json.encodeToJsonElement(order)
    val response = client.post(ApiUrls.orders, orderJson, bearerToken)
    val content = response.readContent()
    // should check for success 201 CREATED, but im not
    return RobinhoodShell.Json.decodeFromString(content)
}
