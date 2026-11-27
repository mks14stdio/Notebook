package ru.mks14.notebook.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import notebook.shared.generated.resources.Res
import notebook.shared.generated.resources.create_new_note
import notebook.shared.generated.resources.ic_add
import notebook.shared.generated.resources.user_notes
import notebook.shared.generated.resources.user_notes_count
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ru.mks14.notebook.domain.model.Note
import ru.mks14.notebook.domain.model.NoteId
import ru.mks14.notebook.ui.navigation.Home
import kotlin.time.Clock

fun EntryProviderScope<NavKey>.mainScreen(onNoteClick: (NoteId?) -> Unit) {
    entry<Home> {
        val viewModel: MainScreenViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        when (val currentState = state) {
            is MainScreenState.Loading -> {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
            is MainScreenState.Loaded -> {
                MainScreenContent(currentState.notes, onNoteClick)
            }
        }
    }
}

@Composable
private fun MainScreenContent(notes: List<Note>, onNoteClick: (NoteId?) -> Unit) {
    Scaffold(
        topBar = {
            TopBar(notes.size)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNoteClick(null) }
            ) {
                Icon(painter = painterResource(Res.drawable.ic_add),
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    contentDescription = stringResource(Res.string.create_new_note))
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notes, key = { item -> item.id.id }) { item ->
                    NoteCard(
                        title = item.title,
                        content = item.content,
                        onClick = { onNoteClick(item.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteCard(
    title: String,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (content.isNotBlank()) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun TopBar(count: Int) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(Res.string.user_notes),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(Res.string.user_notes_count, count),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val notes: List<Note> = buildList {
        repeat(10) {
            add(
                Note(
                    id = NoteId(it.toString()),
                    title = "Заметка №$it",
                    content = "Это пример содержимого заметки.",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now()
                )
            )
        }
    }

    MaterialTheme {
        MainScreenContent(notes, {})
    }
}