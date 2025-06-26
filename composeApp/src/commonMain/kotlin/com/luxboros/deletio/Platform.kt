package com.luxboros.deletio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform