package httpclient
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.JsonElement

class HttpClient(engine: HttpClientEngine) : AutoCloseable {
    val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
            connectTimeoutMillis = 5000
        }
        expectSuccess = false
    }

    suspend fun get(
        url: String,
        bearerToken: String? = ""
    ): HttpResponse {
        return client.get {
            url(url)
            headers {
                header("Authorization", "Bearer $bearerToken")
            }
        }
    }

    suspend fun post(
        url: String,
        payload: JsonElement,
        bearerToken: String? = null
    ): HttpResponse {
        client.post<HttpResponse> {
            url(url)
            headers {
                header("Accept", "application/json")
                header("content-type", "application/json; charset=utf-8")
                header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36 Edg/100.0.1185.39")
                header("X-Robinhood-API-Version", "1.431.4")
                bearerToken?.let { header("Authorization", "Bearer $bearerToken") }
            }
            this.body = payload
        }.let { response ->
            return response
        }
    }

    override fun close() {
        client.close()
    }
}
