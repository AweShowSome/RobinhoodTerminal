package model

import kotlinx.serialization.SerialName
import java.util.UUID
import kotlinx.serialization.Serializable
import util.OffsetDateTimeSerializer
import util.UUIDSerializer
import java.time.OffsetDateTime

@Serializable
data class User(
    val url: String, // could be URL
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @SerialName("id_info")
    val idInfo: String, // could be URL
    val username: String,
    val email: String,
    @SerialName("email_verified")
    val emailVerified: Boolean,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val origin: Origin,
    @SerialName("profile_name")
    val profileName: String,
    @SerialName("created_at")
    @Serializable(with = OffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime // might need to be something else, use this for reference: https://www.w3.org/TR/NOTE-datetime
)

@Serializable
data class Origin(
    val locality: String
)
