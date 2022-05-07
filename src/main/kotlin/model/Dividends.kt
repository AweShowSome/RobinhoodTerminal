package model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.OffsetDateTimeSerializer
import util.UUIDSerializer
import java.time.OffsetDateTime
import java.util.UUID

@Serializable
data class Dividends(
    val next: String? = null, // have no idea what this is
    val previous: String? = null, // have no idea what this is
    val results: List<DividendResult>
)

@Serializable
data class DividendResult(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String, // could be type URL
    @SerialName("account")
    val account: String, // could be type URL
    @SerialName("instrument")
    val instrument: String, // could be type URL
    @SerialName("amount")
    val amount: Double,
    @SerialName("rate")
    val rate: Double,
    @SerialName("position")
    val position: Double,
    @SerialName("withholding")
    val withholding: Double,
    @SerialName("record_date")
    val recordDate: LocalDate,
    @SerialName("payable_date")
    val payableDate: LocalDate,
    @SerialName("paid_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val paidAt: OffsetDateTime?,
    @SerialName("state")
    val state: String,
    @SerialName("drip_order_id")
    @Serializable(with = UUIDSerializer::class)
    val dripOrderId: UUID?,
    @SerialName("drip_order_quantity")
    val dripOrderQuanitity: Double?,
    @SerialName("drip_order_execution_price")
    val dripOrderExecutionPrice: DripOrderExecutionPrice?,
    @SerialName("drip_enabled")
    val dripEnabled: Boolean,
    @SerialName("nra_withholding")
    val nraWithholding: String, // This is probably a Double, but I don't know what this really is
    @SerialName("is_primary_account")
    val isPrimaryAccount: Boolean
)

@Serializable
data class DripOrderExecutionPrice(
    @SerialName("currency_id")
    @Serializable(with = UUIDSerializer::class)
    val currencyId: UUID,
    @SerialName("currency_code")
    val currencyCode: String,
    @SerialName("amount")
    val amount: Double
)