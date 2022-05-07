package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import util.UUIDSerializer
import java.util.*

@Serializable
data class Applications(
    val next: String? = null, // have no idea what this is
    val previous: String? = null, // have no idea what this is
    val results: List<ApplicationResult>
)

@Serializable // I was feeling lazy and didn't make all the serial names because I technically don't need to for one word variables
data class ApplicationResult(
    @Serializable(with = UUIDSerializer::class)
    val uuid: UUID?,
    val url: String, // could be URL
    val user: String,
    val ready: Boolean,
    @SerialName("account_type")
    val accountType: String,
    @SerialName("customer_type")
    val customerType: String,
    @SerialName("last_error")
    val lastError: String,
    val state: String,
//    val cipQuestions: String, // don't know what this is
    val rejected: Boolean,
    val id: Int
)
