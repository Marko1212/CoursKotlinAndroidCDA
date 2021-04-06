package com.formation.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onClick(v: View) {
        if (v.tag != null) {
            showNoteDetail(v.tag as Int)
        }
    }

    private fun showNoteDetail(position: Int) {
    val note = notes[position]
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, position)
        startActivity(intent)
    }
}

