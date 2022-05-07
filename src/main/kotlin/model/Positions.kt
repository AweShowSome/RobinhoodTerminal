package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.OffsetDateTimeSerializer
import util.UUIDSerializer
import java.time.OffsetDateTime
import java.util.UUID

@Serializable
data class Positions(
    val next: String? = null, // have no idea what this is
    val previous: String? = null, // have no idea what this is
    val results: List<PositionResult>
)

@Serializable
data class PositionResult(
    @SerialName("url")
    val url: String, // could be URL
    @SerialName("instrument")
    val instrument: String, // could be URL
    @SerialName("instrument_id")
    @Serializable(with = UUIDSerializer::class)
    val instrumentId: UUID,
    @SerialName("account")
    val account: String, // could be URL
    @SerialName("account_number")
    val accountNumber: String,
    @SerialName("average_buy_price")
    val averageBuyPrice: Double,
    @SerialName("pending_average_buy_price")
    val pendingAverageBuyPrice: Double,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("intraday_average_buy_price")
    val intradayAverageBuyPrice: Double,
    @SerialName("intraday_quantity")
    val intradayQuantity: Double,
    @SerialName("shares_available_for_exercise")
    val sharesAvailableForExercise: Double,
    @SerialName("shares_held_for_buys")
    val sharesHeldForBuys: Double,
    @SerialName("shares_held_for_sells")
    val sharesHeldForSells: Double,
    @SerialName("shares_held_for_stock_grants")
    val sharesHeldForStockGrants: Double,
    @SerialName("shares_held_for_options_collateral")
    val sharesHeldForOptionsCollateral: Double,
    @SerialName("shares_held_for_options_events")
    val sharesHelpForOptionsEvents: Double,
    @SerialName("shares_pending_from_options_events")
    val sharesPendingFromOptionsEvents: Double,
    @SerialName("shares_available_for_closing_short_position")
    val sharesAvailableForClosingShortPosition: Double,
    @SerialName("ipo_allocated_quantity")
    val ipoAllocatedQuantity: Double,
    @SerialName("ipo_dsp_allocated_quantity")
    val ipoDspAllocatedQuantity: Double,
    @SerialName("avg_cost_affected")
    val avgCostAffected: Boolean,
//      @SerialName("avg_cost_affected_reason")
//      val avgCostAffectedReason: List<String>, // dont know what this is but sometimes is null, sometimes empty list
    @SerialName("is_primary_account")
    val isPrimaryAccount: Boolean,
    @SerialName("updated_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val updatedAt: OffsetDateTime,
    @SerialName("created_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime
)