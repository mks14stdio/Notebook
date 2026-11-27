package ru.mks14.notebook

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
    private val platform: Platform by inject()

    fun greet(): String {
        return sayHello(platform.name)
    }
}
