package ru.mks14.notebook.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

import org.koin.compose.koinInject
import ru.mks14.notebook.Platform
import ru.mks14.notebook.data.repository.NoteRepositoryImpl
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.repository.NoteRepository
import ru.mks14.notebook.ui.navigation.AppNavHost
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}