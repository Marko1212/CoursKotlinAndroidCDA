package com.formation.instagramfirebaseandroid

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.formation.instagramfirebaseandroid.models.Post
import com.formation.instagramfirebaseandroid.models.User
import kotlinx.android.synthetic.main.activity_create.*

private const val TAG = "CreateActivity"
private const val PICK_PHOTO_CODE = 1234
class CreateActivity : AppCompatActivity() {
    private var signedInUser: User? = null
    private var photoUri: Uri? = null
    private lateinit var firestoreDb: FirebaseFirestore
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        storageReference = FirebaseStorage.getInstance().reference
        firestoreDb = FirebaseFirestore.getInstance()
        firestoreDb.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
            .get()
            .addOnSuccessListener { userSnapshot ->
                signedInUser = userSnapshot.toObject(User::class.java)
                Log.i(TAG, "utilisateur connecté: $signedInUser")
            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "Échec de la récupération de l'utilisateur connecté", exception)
            }

        btnPickImage.setOnClickListener {
            Log.i(TAG, "Ouvrir le sélectionneur d'images sur l'appareil")
            val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
            imagePickerIntent.type = "image/*"
            if (imagePickerIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(imagePickerIntent, PICK_PHOTO_CODE)
            }
        }

        btnSubmit.setOnClickListener {
            handleSubmitButtonClick()
        }
    }

    private fun handleSubmitButtonClick() {
        if (photoUri == null) {
            Toast.makeText(this, "Pas de photo sélectionnée", Toast.LENGTH_SHORT).show()
            return
        }
        if (etDescription.text.isBlank()) {
            Toast.makeText(this, "La description ne peut pas être vide", Toast.LENGTH_SHORT).show()
            return
        }
        if (signedInUser == null) {
            Toast.makeText(this, "Pas d'utilisateur connecté, attendez...", Toast.LENGTH_SHORT).show()
            return
        }

        btnSubmit.isEnabled = false
        val photoUploadUri = photoUri as Uri
        val photoReference = storageReference.child("images/${System.currentTimeMillis()}-photo.jpg")
        // Upload de la photo à Firebase Storage
        photoReference.putFile(photoUploadUri)
            .continueWithTask { photoUploadTask ->
                Log.i(TAG, "bytes uploadés : ${photoUploadTask.result?.bytesTransferred}")
                // Récupérer image url de l'image uploadée
                photoReference.downloadUrl
            }.continueWithTask { downloadUrlTask ->
                // Création de l'objet post avec image url et rajout du post à la collection des posts sur Firestore
                val post = Post(
                    etDescription.text.toString(),
                    downloadUrlTask.result.toString(),
                    System.currentTimeMillis(),
                    signedInUser)
                firestoreDb.collection("posts").add(post)
            }.addOnCompleteListener { postCreationTask ->
                btnSubmit.isEnabled = true
                if (!postCreationTask.isSuccessful) {
                    Log.e(TAG, "Exception pendant les opérations Firebase", postCreationTask.exception)
                    Toast.makeText(this, "Echec de l'enregistrement du post", Toast.LENGTH_SHORT).show()
                }
                etDescription.text.clear()
                imageView.setImageResource(0)
                Toast.makeText(this, "Succès!", Toast.LENGTH_SHORT).show()
                val profileIntent = Intent(this, ProfileActivity::class.java)
                profileIntent.putExtra(EXTRA_USERNAME, signedInUser?.username)
                startActivity(profileIntent)
                finish()
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                photoUri = data?.data
                Log.i(TAG, "photoUri $photoUri")
                imageView.setImageURI(photoUri)
            } else {
                Toast.makeText(this, "Action du sélectionneur d'images annulée", Toast.LENGTH_SHORT).show()
            }
        }
    }
}