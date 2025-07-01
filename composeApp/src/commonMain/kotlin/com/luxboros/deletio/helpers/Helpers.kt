package com.luxboros.deletio.helpers

fun Double.toCurrencyString(): String {
    val value = (this * 100).toLong() / 100.0
    val parts = value.toString().split(".")
    val integers = parts[0]
    val decimals = parts.getOrNull(1)?.padEnd(2, '0') ?: "00"
    return "$integers.$decimals"
}