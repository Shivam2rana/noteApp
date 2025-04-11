package com.example.noteapp.domain.util

import com.example.noteapp.data.local.NoteEntity
import com.example.noteapp.domain.model.Note


fun NoteEntity.toDomain() = Note(id, title, content)
fun Note.toEntity() = NoteEntity(id, title, content)
