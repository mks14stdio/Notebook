package ru.mks14.notebook.domain.model

import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Note(
    val id: NoteId,
    val title: String,
    val content: String,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    init {
        require(updatedAt >= createdAt) {
            "updatedAt cannot be earlier than createdAt"
        }
    }

    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun create(title: String, content: String): Note {
            val id = Uuid.generateV7().toString()
            return Note(id = NoteId(id), title = title, content = content,
                createdAt = Clock.System.now(), updatedAt = Clock.System.now()
                )
        }
    }
}