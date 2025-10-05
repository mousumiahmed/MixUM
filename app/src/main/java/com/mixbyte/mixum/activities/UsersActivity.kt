package com.mixbyte.mixum.activities
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mixbyte.mixum.R
import com.mixbyte.mixum.adapters.UserAdapter
import com.mixbyte.mixum.models.User
import com.google.firebase.firestore.FirebaseFirestore

class UsersActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private val userList = ArrayList<User>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        recyclerView = findViewById(R.id.usersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        db.collection("users").get().addOnSuccessListener { documents ->
            userList.clear()
            for(doc in documents){
                val user = doc.toObject(User::class.java)
                userList.add(user)
            }
            adapter.notifyDataSetChanged()
        }
    }
}
