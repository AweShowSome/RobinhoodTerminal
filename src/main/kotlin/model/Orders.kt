package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.OffsetDateTimeSerializer
import util.UUIDSerializer
import java.time.OffsetDateTime
import java.util.*

@Serializable
data class Orders(
    @SerialName("account")
    val account: String,
    @SerialName("instrument")
    val instrument: String, // could be URL
    @SerialName("price")
    val price: Double,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("ref_id")
    @Serializable(with = UUIDSerializer::class)
    val refId: UUID = UUID.randomUUID(),
    @SerialName("side")
    val side: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("time_in_force")
    val timeInForce: String,
    @SerialName("trigger")
    val trigger: String,
    @SerialName("type")
    val type: String
)

@Serializable
data class OrdersResponse(
    @SerialName("id")
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @SerialName("ref_id")
    @Serializable(with = UUIDSerializer::class)
    val refId: UUID = UUID.randomUUID(),
    @SerialName("url")
    val url: String, // could be URL
    @SerialName("account")
    val account: String, // could be URL
    @SerialName("position")
    val position: String, // could be URL
    @SerialName("cancel")
    val cancel: String, // could be URL
    @SerialName("instrument")
    val instrument: String, // could be URL
    @SerialName("instrument_id")
    @Serializable(with = UUIDSerializer::class)
    val instrumentId: UUID,
    @SerialName("cumulative_quantity")
    val cumulativeQuantity: Double,
    @SerialName("average_price")
    val averagePrice: Double?,
    @SerialName("fees")
    val fees: Double,
    @SerialName("state")
    val state: String,
    @SerialName("pending_cancel_open_agent")
    val pendingCancelOpenAgent: String?,
    @SerialName("type")
    val type: String,
    @SerialName("side")
    val side: String,
    @SerialName("time_in_force")
    val timeInForce: String,
    @SerialName("trigger")
    val trigger: String,
    @SerialName("price")
    val price: Double?,
    @SerialName("stop_price")
    val stopPrice: Double?,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("reject_reason")
    val rejectReason: String?,
    @SerialName("created_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime,
    @SerialName("updated_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val updatedAt: OffsetDateTime,
    @SerialName("last_transaction_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val lastTransactionAt: OffsetDateTime,
    @SerialName("executions")
    val executions: List<String> = emptyList(),
    @SerialName("extended_hours")
    val extendedHours: Boolean,
    @SerialName("override_dtbp_checks")
    val overrideDtbpChecks: Boolean,
    @SerialName("override_day_trade_checks")
    val overrideDayTradeChecks: Boolean,
    @SerialName("response_category")
    val responseCategory: String?,
    @SerialName("stop_triggered_at")
    val stopTriggeredAt: String?, // probably a Double
    @SerialName("last_trail_price")
    val lastTrailPrice: String?,
    @SerialName("last_trail_price_updated_at")
    val lastTrailPriceUpdatedAt: String?,
    @SerialName("last_trail_price_source")
    val lastTrailPriceSource: String?,
    @SerialName("dollar_based_amount")
    val dollarBasedAmount: String?,
    @SerialName("drip_dividend_id")
    val dripDividendId: String?,
    @SerialName("total_notional")
    val totalNotional: TotalNotional?,
    @SerialName("executed_notional")
    val executedNotional: String?,
    @SerialName("investment_schedule_id")
    val investmentScheduleId: String?, // might be UUID
    @SerialName("is_ipo_access_order")
    val isIpoAccessOrder: Boolean,
    @SerialName("ipo_access_cancellation_reason")
    val ipoAccessCancellationReason: String?,
    @SerialName("ipo_access_lower_collared_price")
    val ipoAccessLowerCollaredPrice: String?,
    @SerialName("ipo_access_upper_collared_price")
    val ipoAccessUpperCollaredPrice: String?,
    @SerialName("ipo_access_upper_price")
    val ipoAccessUpperPrice: String?,
    @SerialName("ipo_access_lower_price")
    val ipoAccessLowerPrice: String?,
    @SerialName("is_ipo_access_price_finalized")
    val isIpoAccessPriceFinalized: Boolean,
    @SerialName("is_visible_to_user")
    val isVisibleToUser: Boolean,
    @SerialName("has_ipo_access_custom_price_limit")
    val hasIpoAccessCustomPriceLimit: Boolean,
    @SerialName("is_primary_account")
    val isPrimaryAccount: Boolean,
)

@Serializable
data class TotalNotional(
    @SerialName("amount")
    val amount: Double,
    @SerialName("currency_code")
    val currencyCode: String,
    @SerialName("currency_id")
    val currencyId: String
)

enum class OrderSide {
    buy,
    sell
}