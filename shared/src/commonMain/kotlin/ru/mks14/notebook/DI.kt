package ru.mks14.notebook

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import ru.mks14.notebook.data.di.repositoryModule
import ru.mks14.notebook.ui.di.vmModule

val appModule = module {
    includes(getPlatform(), repositoryModule, vmModule)
}

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        includes(configuration)
        modules(appModule)
        printLogger(Level.DEBUG)
    }
}