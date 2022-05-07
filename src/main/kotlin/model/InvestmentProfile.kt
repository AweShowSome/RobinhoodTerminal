package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.OffsetDateTimeSerializer
import java.time.OffsetDateTime

@Serializable
data class InvestmentProfile(
    @SerialName("user")
    val user: String,
    @SerialName("total_net_worth")
    val totalNetWorth: String,
    @SerialName("annual_income")
    val annualIncome: String,
    @SerialName("source_of_funds")
    val sourcesOfFunds: String,
    @SerialName("investment_objective")
    val investmentObjective: String,
    @SerialName("investment_experience")
    val investmentExperience: String,
    @SerialName("liquid_net_worth")
    val liquidNetWorth: String,
    @SerialName("risk_tolerance")
    val riskTolerance: String,
    @SerialName("tax_bracket")
    val taxBracket: String,
    @SerialName("time_horizon")
    val timeHorizon: String,
    @SerialName("liquidity_needs")
    val liquidityNeeds: String,
    @SerialName("investment_experience_collected")
    val investmentExperienceCollected: Boolean,
    @SerialName("suitability_verified")
    val suitabilityVerified: Boolean,
    @SerialName("option_trading_experience")
    val optionTradingExperience: String,
    @SerialName("professional_trader")
    val professionalTrader: Boolean,
    @SerialName("understand_option_spreads")
    val understandOptionSpreads: Boolean,
    @SerialName("interested_in_options")
    val interestedInOptions: String? = null, // do not know what this object should be, probably boolean
    @SerialName("updated_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val updatedAt: OffsetDateTime
)
