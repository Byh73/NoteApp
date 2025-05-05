package com.example.noteapp

import android.app.Application
import androidx.room.Room
import com.example.noteapp.utils.PreferenceHelper
import com.example.noteapp.ui.fragments.data.database.AppDatabase

class App : Application() {
    companion object {
        var appDatabase : AppDatabase? = null
        lateinit var  sharedPreference : PreferenceHelper
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreference = PreferenceHelper()
        sharedPreference.unit(this)
        getInstance()
    }

    private fun getInstance() : AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase

    }
}