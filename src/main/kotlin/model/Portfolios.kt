package model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Portfolios(
    @SerialName("results")
    val results: List<PortfolioResult>
)

@Serializable
data class PortfolioResult(
    @SerialName("url")
    val url: String,
    @SerialName("account")
    val account: String,
    @SerialName("start_date")
    @Contextual
//    @Serializable(with = LocalDateComponentSerializer::class)
    val startDate: LocalDate,
    @SerialName("market_value")
    val marketValue: Double,
    val equity: Double,
    @SerialName("extended_hours_market_value")
    val extendedHoursMarketValue: Double,
    @SerialName("extended_hours_equity")
    val extendedHoursEquity: Double,
    @SerialName("extended_hours_portfolio_equity")
    val extendedHoursPortfolioEquity: Double,
    @SerialName("last_core_market_value")
    val lastCoreMarketValue: Double,
    @SerialName("last_core_equity")
    val lastCoreEquity: Double,
    @SerialName("last_core_portfolio_equity")
    val lastCorePortfolioEquity: Double,
    @SerialName("excess_margin")
    val excessMargin: Double,
    @SerialName("excess_maintenance")
    val excessMaintenance: Double,
    @SerialName("excess_margin_with_uncleared_deposits")
    val excessMarginWithUnclearedDeposits: Double,
    @SerialName("excess_maintenance_with_uncleared_deposits")
    val excessMaintenanceWithUnclearedDeposits: Double,
    @SerialName("equity_previous_close")
    val equityPreviousClose: Double,
    @SerialName("portfolio_equity_previous_close")
    val portfolioEquityPreviousClose: Double,
    @SerialName("adjusted_equity_previous_close")
    val adjustedEquityPreviousClose: Double,
    @SerialName("adjusted_portfolio_equity_previous_close")
    val adjustedPortfolioEquityPreviousClose: Double,
    @SerialName("withdrawable_amount")
    val withdrawableAmount: Double,
    @SerialName("unwithdrawable_deposits")
    val unwithdrawableDeposits: Double,
    @SerialName("unwithdrawable_grants")
    val unwithdrawableGrants: Double,
    @SerialName("is_primary_account")
    val isPrimaryAccount: Boolean,
)