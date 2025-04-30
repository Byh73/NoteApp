package com.example.noteapp.ui.fragments.note

import com.example.noteapp.ui.fragments.data.models.Note


interface OnClickNote {
   fun onLongClick(note: Note)
   fun onClick(note : Note)
}