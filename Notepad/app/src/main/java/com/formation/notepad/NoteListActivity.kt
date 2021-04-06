package com.formation.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NoteListActivity : AppCompatActivity() {
    
    lateinit var notes : MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf()
        notes.add(Note("Note 1", "Blablablabla"))
        notes.add(Note("Course", "ne pas oublier de prendre des biscuits"))
        notes.add(
            Note(
                "Android Kotlin",
                "C'est cool de faire de l'android, même si ce n'est pas toujours simple"
            )
        )
        notes.add(
            Note(
                "Flutter",
                "C'est cool aussi d'utiliser un framework pour coder une application sur différents appareils"
            )
        )
    }
}