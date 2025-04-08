package com.example.noteapp.domain.repo

import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun insertNote(note: Note): Long
    suspend fun deleteNote(note: Note)
}