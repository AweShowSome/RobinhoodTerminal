package model

data class ApplicationConfig(
    val credentials: Credentials,
    val session: Session
)

data class Credentials(
    val username: String,
    val password: String
)

data class Session(
    val accessToken: String,
    val expiresIn: Int,
    val tokenType: String = "Bearer",
    val scope: String = "internal",
    val refreshToken: String,
    val account: String
)