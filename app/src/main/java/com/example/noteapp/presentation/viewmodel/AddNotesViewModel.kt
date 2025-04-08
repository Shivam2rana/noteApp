package com.example.noteapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import  androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.launch

@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    var title by mutableStateOf("")
        private set   //this makes sure only viewmodel can modify it

    var content by mutableStateOf("")
        private set

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onContentChange(newContent: String) {
        content = newContent
    }


    suspend fun saveNoteIfValid(): Long? {
        if (title.isBlank() && content.isBlank()) return null

        val note = Note(title = title.trim(), content = content.trim())
        val id = noteUseCases.insertNote(note)
        title = ""
        content = ""
        return id
    }
}
