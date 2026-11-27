package ru.mks14.notebook.data.di

import org.koin.dsl.module
import ru.mks14.notebook.data.repository.NoteRepositoryImpl
import ru.mks14.notebook.domain.repository.NoteRepository

val repositoryModule = module {
    single<NoteRepository> {
        NoteRepositoryImpl()
    }
}