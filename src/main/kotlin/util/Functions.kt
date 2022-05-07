package util

import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.jvm.javaio.toInputStream
import java.math.RoundingMode
import kotlin.math.roundToInt

fun HttpResponse.readContent(): String {
    return this.content.toInputStream().bufferedReader().use { it.readLine() }
}

fun CharArray.readContent(): String {
    return String(this)
}

fun Double.round(numberOfDecimals: Int = 6): Double {
    return this.toBigDecimal().setScale(numberOfDecimals, RoundingMode.HALF_UP).toDouble()
}

fun String.canBeDouble(): Boolean {
    return try {
        this.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun String.canBeInt(): Boolean {
    return try {
        this.toInt() // trying to do fast computation before doing the more intensive check. I could just use the inner try catch by itself.
        true
    } catch (e: NumberFormatException) {
        try {
            val doubleValue = this.toDouble()
            val intValue = doubleValue.roundToInt()
            if (doubleValue - intValue == 0.0) {
                return true
            }
            return false
        } catch (e: NumberFormatException) {
            false
        }
    }
}

fun readInputSafely(prompt: String, hiddenInput: Boolean = false): String {
    var response = ""
    while (response.isBlank()) {
        print(prompt)
        response = if (hiddenInput) {
            System.console()?.readPassword()?.readContent() ?: readLine() ?: ""
        } else {
            readLine() ?: ""
        }
    }
    println()
    return response
}