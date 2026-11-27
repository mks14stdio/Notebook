package ru.mks14.notebook

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.resources.painterResource

import notebook.shared.generated.resources.Res
import notebook.shared.generated.resources.compose_multiplatform
import org.koin.compose.koinInject
import ru.mks14.notebook.data.repository.NoteRepositoryImpl
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.repository.NoteRepository
import kotlin.time.Duration.Companion.milliseconds


sealed interface AppState {
    object Loading : AppState
    data class Success(val notes: List<Note>) : AppState
}
@Composable
@Preview
fun App(platform: Platform = koinInject()) {
    val repository: NoteRepository = remember {  NoteRepositoryImpl() }
    val notes by repository.observerNotes().map {
        notes ->
        delay(3000.milliseconds)
        AppState.Success(notes)
    }.collectAsStateWithLifecycle(AppState.Loading)

    MaterialTheme {
        Scaffold {
            innerPadding ->
            Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                if (notes is AppState.Loading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    Text(platform.name)
                }
            }
        }


    }
}