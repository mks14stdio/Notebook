package ru.mks14.notebook

import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Module