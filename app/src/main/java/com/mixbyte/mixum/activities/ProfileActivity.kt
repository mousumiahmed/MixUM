package com.mixbyte.mixum.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mixbyte.mixum.models.User
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mixbyte.mixum.R
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    private val PICK_IMAGE = 100
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameEt = findViewById<EditText>(R.id.nameEt)
        val ageEt = findViewById<EditText>(R.id.ageEt)
        val genderEt = findViewById<EditText>(R.id.genderEt)
        imageView = findViewById(R.id.profileImageIv)
        val uploadBtn = findViewById<Button>(R.id.uploadBtn)

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        uploadBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val age = ageEt.text.toString().toIntOrNull() ?: 0
            val gender = genderEt.text.toString()
            val uid = auth.currentUser?.uid ?: return@setOnClickListener

            if(imageUri != null){
                val ref = storage.reference.child("profile_images/${UUID.randomUUID()}")
                ref.putFile(imageUri!!).addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        val user = User(uid, name, age, gender, uri.toString())
                        db.collection("users").document(uid).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, UsersActivity::class.java))
                                finish()
                            }
                    }
                }
            } else {
                Toast.makeText(this, "Select Profile Image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data?.data
            Glide.with(this).load(imageUri).into(imageView)
        }
    }
}
