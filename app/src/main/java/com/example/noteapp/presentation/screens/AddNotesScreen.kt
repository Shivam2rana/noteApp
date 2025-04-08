package com.example.noteapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.presentation.viewmodel.AddNotesViewModel
import kotlinx.coroutines.launch

@Composable
fun AddNotesScreen(
    viewModel: AddNotesViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.title,
            onValueChange = viewModel::onTitleChange,
            label = { Text("Title") }
        )

        TextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            value = viewModel.content,
            onValueChange = viewModel::onContentChange,
            label = { Text("Note") }
        )

        Button(
            onClick = {
                scope.launch {
                    val id = viewModel.saveNoteIfValid()
                    if (id!=null) {
                        Log.d("NoteApp", "Note inserted with ID: $id")
                    }else{
                        Log.d("NoteApp", "Note not inserted")
                    }
                }
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text("Save Note")
        }
    }
}
