package ru.mks14.notebook.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.repository.NoteRepository

sealed interface MainScreenState {
    object Loading : MainScreenState
    data class Loaded(val notes: List<Note>)
}

class MainScreenViewModel(
    noteRepository: NoteRepository
) : ViewModel() {
    val state = noteRepository.observerNotes().map {
        notesList ->
        MainScreenState.Loaded(notesList)
    }.stateIn(
        initialValue = MainScreenState.Loading,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}