package model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.UUIDSerializer
import java.util.UUID

@Serializable
data class Quotes(
    @SerialName("ask_price")
    val askPrice: Double,
    @SerialName("ask_size")
    val askSize: Int,
    @SerialName("bid_price")
    val bidPrice: Double,
    @SerialName("bid_size")
    val bidSize: Int,
    @SerialName("last_trade_price")
    val lastTradePrice: Double,
    @SerialName("last_extended_hours_trade_price")
    val lastExtendedHoursTradePrice: Double,
    @SerialName("previous_close")
    val previousClose: Double,
    @SerialName("adjusted_previous_close")
    val adjustedPreviousClose: Double,
    @SerialName("previous_close_date")
    val previousCloseDate: LocalDate,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("trading_halted")
    val tradingHalted: Boolean,
    @SerialName("has_traded")
    val hasTraded: Boolean,
    @SerialName("last_trade_price_source")
    val lastTradePriceSource: String,
    @SerialName("updated_at")
    val updatedAt: String, // DATE FORMAT: 2022-04-19T20:29:06Z
    @SerialName("instrument")
    val instrument: String, // could be URL
    @SerialName("instrument_id")
    @Serializable(with = UUIDSerializer::class)
    val instrumentId: UUID?,
    @SerialName("state")
    val state: String
)
