package com.example.noteapp.ui.models

import java.io.Serializable

data class OnboardPage (
    val animation : Int,
    val title : String,
    val text : String
) : Serializable