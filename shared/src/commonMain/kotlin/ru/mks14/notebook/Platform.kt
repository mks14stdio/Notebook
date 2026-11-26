package ru.mks14.notebook

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform