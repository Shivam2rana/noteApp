package com.example.noteapp.di

import com.example.noteapp.data.NoteDao
import com.example.noteapp.domain.repo.NoteRepository
import com.example.noteapp.domain.repo.NoteRepositoryImpl
import com.example.noteapp.domain.usecase.DeleteNote
import com.example.noteapp.domain.usecase.GetNotes
import com.example.noteapp.domain.usecase.InsertNote
import com.example.noteapp.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

    @Provides
    fun provideNoteUseCases(repo: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repo),
            insertNote = InsertNote(repo),
            deleteNote = DeleteNote(repo)
        )
    }
}
