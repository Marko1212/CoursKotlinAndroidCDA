package com.formation.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.formation.instagram.utils.GlideBlurTransformation
import com.formation.instagram.utils.PhotoUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreatePostActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val PICK_IMAGE = 100
    }
    lateinit var sp: SharedPreferences
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var nickname: TextView
    lateinit var description: EditText
    lateinit var descriptionCount: TextView
    lateinit var btnOpenGallery: Button
    lateinit var photoBlur: ImageView
    lateinit var photo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        bottomNavigationViewAction()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this)

        sp = getSharedPreferences("User", Context.MODE_PRIVATE)

        nickname = findViewById(R.id.nickname)
        description = findViewById(R.id.description)
        descriptionCount = findViewById(R.id.description_count)
        btnOpenGallery = findViewById(R.id.btn_open_gallery)
        photoBlur = findViewById(R.id.photo_blur)
        photo = findViewById(R.id.photo)

        nickname.text = sp.getString("nickname", "")
        getDescriptionForTextLength()

        btnOpenGallery.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_open_gallery -> {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, PICK_IMAGE)
            }

            R.id.fab -> {
                validationPost()
            }

        }

    }

    private fun validationPost() {
        if(nickname.text.isNotEmpty() && description.text.length > 2 && photo.isDirty) {

            Log.i("validationPost", "Tout est ok. Je vais pouvoir valider dans ma bdd")
            return
        }

        Toast.makeText(nickname.context, "Il te manque des informations", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Log.i("CreatePostActivity", "${data?.data}")

            val photoURI = data?.data!!


            var bitmap: Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, photoURI))
            } else {
                MediaStore.Images.Media.getBitmap(this.contentResolver, photoURI)
            }

            bitmap = PhotoUtils().resize(bitmap, 1200, 1200)

          //  println("height: ${bitmap.height}, width: ${bitmap.width}, size: ${bitmap.byteCount}")


            photo.setImageBitmap(bitmap)

            val context = photo.context

            Glide.with(context)
                .asBitmap()
                .load(bitmap)
                .transform(
                    GlideBlurTransformation(context)
                )
                .into(photoBlur)



        }
    }

    private fun getDescriptionForTextLength() {

        descriptionCount.text = getString(R.string.description_text_length, 0)
        description.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            descriptionCount.text = getString(R.string.description_text_length, s?.length)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun bottomNavigationViewAction(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView
            .menu
            .findItem(R.id.action_void).isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, PostListActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_user -> {
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }

        }
    }

}