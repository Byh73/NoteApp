package com.example.noteapp.ui.fragments.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.ui.fragments.data.database.dao.NoteDao
import com.example.noteapp.ui.fragments.data.models.Note

@Database(entities = [Note::class  ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}