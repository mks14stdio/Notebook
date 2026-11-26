package ru.mks14.notebook.domain.model

import kotlin.time.Instant

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
}