package com.example.noteapp.ui.fragments.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class  Note (
    val title : String,
    val text : String,
    val date : String,
    val color : Int
) {
    @PrimaryKey(autoGenerate = true )
    var id : Int = 0
}
