package com.example.noteapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    NotesList(modifier = modifier, notes = notes)
}

@Composable
fun NotesList(modifier: Modifier = Modifier, notes: List<Note>) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(top = 16.dp, bottom = 0.dp),
        columns = StaggeredGridCells.Adaptive(180.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(notes.size) { pos ->
                NotesItem(notes[pos].title, notes[pos].content)
            }
        }
    )
}

@Composable
fun NotesItem(title: String, content: String) {
    OutlinedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(4.dp)
                )
            }
            if (content.isNotEmpty()) {
                Text(
                    text = content,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

    }
}
