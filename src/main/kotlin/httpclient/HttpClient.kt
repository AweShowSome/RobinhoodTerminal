package httpclient
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.JsonElement
import okhttp3.Headers.Companion.toHeaders

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

    @OptIn(InternalAPI::class)
    suspend fun get(
        url: String,
        headers: Map<String, String> = emptyMap(),
//        bearerToken: String
//        headerss: List<String> = emptyList()
    ): HttpResponse {
//        val aosdf =
        val oksdfaasdfasdf = HttpRequestBuilder().headers { headers.toHeaders() }
        println(oksdfaasdfasdf)
//        println(headers)
        val oc = EmptyContent
        val executionContext: Job = SupervisorJob()

        val thinggggg = HttpRequestData(
            Url(url),
            HttpMethod.Get,
            headers = Headers.build { oksdfaasdfasdf.build() },
            oc,
            executionContext,
            Attributes(true))

        return client.get(HttpRequestBuilder().takeFrom(thinggggg))

//        client.request<HttpResponse>(url) {
//            method = HttpMethod.Get
//            headers { headers.toHeaders() }
//        }
//
//        return client.get(url) {
//            headers {
//
////                apply { oksdfaasdfasdf }
//            }
//
//        }
//
//        client.request<HttpResponse>(url) {
//
//        }

//        return asdlfkjasdf

//        val thinggggg = HttpRequestData(
//            Url(url),
//            HttpMethod.Get,
//            headers = Headers.build { oksdfaasdfasdf.build() },
//            oc,
//            executionContext,
//            Attributes(true))
////
//
////            oksdfaasdfasdf,
//
////            headers.toHeaders(),
//
//        )
//
//        HttpRequestBuilder().build().headers
//        val thinggg = HttpRequestBuilder(url)
//
//        val thing = client.get {
//            url(url)
////            headers.toHeaders()
////            headers {
////                headers.toHeaders()
////            }
//            headers {
//////                headersOf(headerss)
//                header("Authorization", "Bearer $bearerToken")
//            }
////            headerss
////            headers {
////                headers(headerss)
////            }
//        }
//        thing
    }

    suspend fun post(
        url: String,
        payload: JsonElement,
        bearerToken: String? = null
    ): HttpResponse {
        client.post<HttpResponse>{
//            println("POST")
//            println(payload)
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
//            println(response.status)
//            println(response.content)
//            println(response.content.toInputStream().bufferedReader().use { it.readLine() })
            return response
//            println(response.content.toInputStream().buffered().use { it.readBytes() })
        }
//        """
//            date: Mon, 11 Apr 2022 22:43:52 GMT
//            content-type: application/json
//            content-length: 48
//            server: envoy
//            allow: POST, OPTIONS
//            x-robinhood-api-version: 0.0.0
//            content-security-policy: default-src 'none'
//            x-frame-options: SAMEORIGIN
//            x-content-type-options: nosniff
//            x-xss-protection: 1; mode=block
//            trace-uuid: some random UUID
//            x-envoy-upstream-service-time: 63
//        """.trimIndent()
//        client.request<>(HttpMethod.Post, "/login") {
//            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
//            setBody(listOf("userId" to "test1", "password" to password).formUrlEncode())
//        }
//
//        HttpMessageBuilder.contentType(ContentType.Text)
//
//
//        val result = client.post("http://127.0.0.1:d/handler") {
//            setBody(
//                MultiPartContent.build {
//                    add("user", "myuser")
//                    add("password", "password")
//                    add("file", byteArrayOf(1, 2, 3, 4), filename = "binary.bin")
//                })
//        }
//        client.request(url) {
//            method = HttpMethod.Post
//            contentType(ContentType.Application.Json)
//            body = payload
//        }
//        client.post(url) {
//            body = payload
//        }
    }
//
//    fun generateHeaders(headers: Map<String, String> = emptyMap()) {
//        HeadersBuilder().apply {
//            val ok = headers.map { set(it.key, it.value) }
//            o
//            set("name", "value")
//        }
//        val thing = headers.map { (header, value) ->
//            val thing = HeadersBuilder().append(header, value).build()
//            thing.
//            HttpRequestBuilder().header(header, value)
//        }
//
//        thing
//
//        HttpRequestBuilder().
//
//        HttpRequestBuilder().headers {
//            headersOf()
//        }
//        HttpRequestBuilder {
//            headers.map { HttpRequestBuilder().headers {header("asdf" to "asdf"}) }
//        }
//        headers.map {
//            HeadersBuilder(header )
//        }
//    }

    override fun close() {
        client.close()
    }
}
