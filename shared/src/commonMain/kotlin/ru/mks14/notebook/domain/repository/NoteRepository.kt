package ru.mks14.notebook.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.model.NoteId

interface NoteRepository {
    fun observerNotes(): Flow<List<Note>>
    fun observerNote(noteId: NoteId) : Flow<Note?>

    suspend fun upsert(note: Note)
}