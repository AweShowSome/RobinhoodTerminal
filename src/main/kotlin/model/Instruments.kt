package model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.UUIDSerializer
import java.util.UUID

@Serializable
data class Instruments(
    @Serializable(with = UUIDSerializer::class)
    @SerialName("id")
    val id: UUID,
    @SerialName("url")
    val url: String, // could be URL
    @SerialName("quote")
    val quote: String, // could be URL
    @SerialName("fundamentals")
    val fundamentals: String, // could be URL
    @SerialName("splits")
    val splits: String, // could be URL
    @SerialName("state")
    val state: String,
    @SerialName("market")
    val market: String, // could be URL
    @SerialName("name")
    val name: String,
    @SerialName("tradeable")
    val tradeable: Boolean,
    @SerialName("tradability")
    val tradability: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("bloomberg_unique")
    val bloombergUnique: String,
    @SerialName("margin_initial_ratio")
    val marginInitialRatio: Double,
    @SerialName("maintenance_ratio")
    val maintenanceRatio: Double,
    @SerialName("country")
    val country: String,
    @SerialName("day_trade_ratio")
    val dayTradeRatio: Double,
    @SerialName("list_date")
    val listDate: LocalDate,
    @SerialName("min_tick_size")
    val minTickSize: String? = null, // dont know what this refers to
    @SerialName("type")
    val type: String,
    @SerialName("tradable_chain_id")
    @Serializable(with = UUIDSerializer::class)
    val tradableChainId: UUID,
    @SerialName("rhs_tradability")
    val rhsTradability: String,
    @SerialName("fractional_tradability")
    val fractionalTradability: String,
    @SerialName("default_collar_fraction")
    val defaultCollarFraction: Double,
    @SerialName("ipo_access_status")
    val ipoAccessStatus: String? = null, // dont know if this is String or Boolean
    @SerialName("ipo_access_cob_deadline")
    val ipoAccessCobDeadline: String? = null, // this probably is a date, but I will use String until I know for certain
    @SerialName("ipo_s1_url")
    val ipoS1Url: String? = null, // dont know for certain, but could probably be URL
    @SerialName("ipo_roadshow_url")
    val ipoRoadshowUrl: String? = null, // dont know for certain, but could probably be URL
    @SerialName("is_spac")
    val isSpac: Boolean,
    @SerialName("is_test")
    val isTest: Boolean,
    @SerialName("ipo_access_supports_dsp")
    val ipoAccessSupportsDsp: Boolean,
    @SerialName("extended_hours_fractional_tradability")
    val extendedHoursFractionalTradability: Boolean
)
