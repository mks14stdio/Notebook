package ru.mks14.notebook.ui.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.mks14.notebook.ui.screen.MainScreenViewModel


val vmModule = module {
    viewModel {
        MainScreenViewModel(get())
    }
}