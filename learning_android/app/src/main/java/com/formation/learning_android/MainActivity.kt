package com.formation.learning_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

   // private val TAG = MainActivity::class.java.simpleName

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenActivityGreen: Button = findViewById(R.id.btn_open_activity_green)

        btnOpenActivityGreen.setOnClickListener{
            val intent = Intent(this, GreenActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            intent.addCategory("UserViewer")

            intent.putExtra("name", "Marko")
            intent.putExtra("age", 47)

            startActivity(intent)
            println("Start green activity")

        }

        Log.v(TAG, "verbose log")
        Log.d(TAG, "debug log")
        Log.i(TAG, "info log")
        Log.w(TAG, "warning log")
        Log.e(TAG, "error log")
        Log.println(Log.ASSERT, TAG, "assert log")

    }


     */

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.show_dialog_button).setOnClickListener {
            val fragment = ConfirmDeleteDialogFragment()

            fragment.listener = object: ConfirmDeleteDialogFragment.ConfirmDeleteListener {
                override fun onDialogPositiveClick() {
                    Log.i("MainActivity", "onDialogPositiveClick()")
                    val fileListDialogFragment = FileListDialogFragment();
                    fileListDialogFragment.show(supportFragmentManager, "fileList")
                }

                override fun onDialogNegativeClick() {
                    Log.i("MainActivity", "onDialogNegativeClick()")
                }

            }

            fragment.show(supportFragmentManager, "confirmDelete")
        }

/*
        findViewById<Button>(R.id.btn_start_activity_user_details).setOnClickListener{
            val intent = Intent(this, UserDetailsActivity::class.java)

            intent.putExtra("user", UserModel("Marko", 47))

            startActivity(intent)
            */

        }


 */

    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> {
                Toast.makeText(this, "Ajouter", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_save -> {
                Toast.makeText(this, "Sauvegarder", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_delete -> {
                Toast.makeText(this, "Supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_help -> {
                Toast.makeText(this, "Aider", Toast.LENGTH_SHORT).show()
                return true
            }


        }

        return super.onOptionsItemSelected(item)
    }

*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Learning Toolbar"
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_delete -> {
                Toast.makeText(this, "Supprimer", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    }
