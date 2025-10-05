package com.mixbyte.mixum.adapters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mixbyte.mixum.R
import com.mixbyte.mixum.models.User

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTv: TextView = itemView.findViewById(R.id.nameTv)
        val ageTv: TextView = itemView.findViewById(R.id.ageTv)
        val genderTv: TextView = itemView.findViewById(R.id.genderEt)
        val profileImage: ImageView = itemView.findViewById(R.id.profileImageIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTv.text = user.name
        holder.ageTv.text = "Age: ${user.age}"
        holder.genderTv.text = "Gender: ${user.gender}"
        Glide.with(holder.itemView.context).load(user.profileImageUrl).into(holder.profileImage)
    }

    override fun getItemCount(): Int = userList.size
}
