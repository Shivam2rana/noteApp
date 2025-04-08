package com.example.noteapp.domain.usecase

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository
import kotlinx.coroutines.flow.Flow

data class NoteUseCases(
    val getNotes: GetNotes,
    val insertNote: InsertNote,
    val deleteNote: DeleteNote
)

class GetNotes(private val repo: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repo.getNotes()
}

class InsertNote(private val repo: NoteRepository) {
    suspend operator fun invoke(note: Note): Long = repo.insertNote(note)
}

class DeleteNote(private val repo: NoteRepository) {
    suspend operator fun invoke(note: Note) = repo.deleteNote(note)
}
