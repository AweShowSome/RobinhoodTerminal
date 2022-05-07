package model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Markets(
    val next: String? = null, // have no idea what this is
    val previous: String? = null, // have no idea what this is
    val results: List<MarketResult>
)

@Serializable // I was feeling lazy and didn't make all the serial names because I technically don't need to for one word variables
data class MarketResult(
    val url: String, // could be URL
    @SerialName("todays_hours")
    val todaysHours: String, // could be URL
    val mic: String,
    @SerialName("operating_mic")
    val operatingMic: String,
    val acronym: String,
    val name: String,
    val city : String,
    val country: String,
    val timezone: String,
    val website: String // could be URL
)
