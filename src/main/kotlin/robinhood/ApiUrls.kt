package robinhood

import java.util.UUID

class ApiUrls {
    companion object {
        const val baseUrl = "https://api.robinhood.com"

        val login = "$baseUrl/oauth2/token/"

        // should implement relogin eventually

        val logout = "$baseUrl/oauth2/revoke_token/"

        val user = "$baseUrl/user/"

        val applications = "$baseUrl/applications/" // stopped working :(

        val investmentProfile = "$baseUrl/user/investment_profile/"

        val dividends = "$baseUrl/dividends/"

        val portfolios = "$baseUrl/portfolios/"

        val positions = "$baseUrl/positions/"

        fun instruments(id: UUID) = "$baseUrl/instruments/$id/"

        fun quotes(ticker: String) = "$baseUrl/quotes/${ticker.uppercase()}/" // could improve by taking a list of tickers

        val markets = "$baseUrl/markets/"

        val orders = "$baseUrl/orders/"

//        val tags = "$baseUrl/tags/"

//        val passwordRequest = "$baseUrl/password_reset/request/"
    }
}
