package ru.mks14.notebook

import android.os.Build
import org.koin.core.module.Module
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Module = module {
    single<Platform> { AndroidPlatform() }
}