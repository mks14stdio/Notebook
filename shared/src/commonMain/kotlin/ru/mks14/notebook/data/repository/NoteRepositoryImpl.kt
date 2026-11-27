package ru.mks14.notebook.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.model.NoteId
import ru.mks14.notebook.domain.repository.NoteRepository
import kotlin.time.Duration.Companion.seconds

class NoteRepositoryImpl : NoteRepository {

    private val notes = MutableStateFlow<List<Note>>(buildList {
        repeat(10) {
            add(Note.create("Заметка №$it", "Это заметка содержит данный контент. Пока что тестовые данные"))
        }
    })

    override fun observerNotes(): Flow<List<Note>> = notes

    override fun observerNote(noteId: NoteId): Flow<Note?> {
        return notes.map {
            notes -> notes.find { it.id == noteId }
        }
    }

    override suspend fun upsert(note: Note) {
        notes.update { currentList ->
        val index = currentList.indexOfFirst { it.id == note.id }
        if (index >= 0) {
            currentList.toMutableList().apply { set(index, note) }
        } else {
            currentList + note
        }
    }
    }
}