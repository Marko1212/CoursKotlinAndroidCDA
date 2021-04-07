package com.formation.notepad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        findViewById<FloatingActionButton>(R.id.create_note_fab).setOnClickListener(this)

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
        if (v.tag != null){
            showNoteDetail(v.tag as Int)
        } else {
            when(v.id) {
                R.id.create_note_fab->createNewNote()
            }
        }
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }

    private fun showNoteDetail(position: Int) {
        val note = if (position < 0) Note() else notes[position]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, position)
        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null){
            return
        }

        when(requestCode){
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }

    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)

        when (data.action) {
            NoteDetailActivity.ACTION_SAVE_NOTE -> {
                val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)!!
                saveNote(note, noteIndex)
            }

            NoteDetailActivity.ACTION_DELETE_NOTE -> {
                deleteNote(noteIndex)
            }
        }

    }

    private fun deleteNote(noteIndex: Int) {
        if (noteIndex < 0) {
            return
        }
        notes.removeAt(noteIndex)
        adapter.notifyDataSetChanged()
    }

    private fun saveNote(note: Note, noteIndex: Int) {
        if (noteIndex < 0) {
            notes.add(0, note)
        } else {
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }
}