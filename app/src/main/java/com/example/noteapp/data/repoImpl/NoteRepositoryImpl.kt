package com.example.noteapp.data.repoImpl

import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.repo.NoteRepository
import com.example.noteapp.domain.util.toDomain
import com.example.noteapp.domain.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> =
        dao.getAllNotes().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insertNote(note: Note) : Long {
       return dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}