package com.formation.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class NoteListActivity : AppCompatActivity(), View.OnClickListener {
    
    lateinit var notes : MutableList<Note>
    lateinit var adapter: NoteAdapter

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

        adapter = NoteAdapter(notes, this)

    }

    override fun onClick(v: View) {
        if (v.tag != null) {
            Log.i("NoteListActivity", "click sur une note de la liste")
        }
    }
}