package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String,
    @SerialName("grant_type")
    val grantType: String = "password",
    @SerialName("challenge_type")
    val challengeType: String = "email",
    @SerialName("expires_in")
    val expiresIn: Int = 86400,
    val scope: String = "internal",
    @SerialName("client_id")
    val clientId: String = "c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS",
    @SerialName("device_token")
    val deviceToken: String = "20513f20-f342-4374-a348-f921e9d2da37", // could be UUID type, also you should generate your own uuid and put it here
    @SerialName("mfa_code")
    val mfaCode: Int? = null
)

@Serializable
data class LoggedIn(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("token_type")
    val tokenType: String = "Bearer",
    @SerialName("scope")
    val scope: String = "internal",
    @SerialName("refresh_token")
    val refreshToken: String
)

fun LoggedIn.toSession(account: String): Session {
    return Session(
        accessToken = accessToken,
        expiresIn = expiresIn,
        tokenType = tokenType,
        scope = scope,
        refreshToken = refreshToken,
        account = account
    )
}
