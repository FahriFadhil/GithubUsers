package com.fahri.githubusers.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahri.githubusers.DetailUserActivity
import com.fahri.githubusers.data.User
import com.fahri.githubusers.databinding.RowItemUserBinding

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(
        val binding: RowItemUserBinding,
        private val listener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            RowItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) { i ->
            parent.context.startActivity(
                Intent(
                    parent.context,
                    DetailUserActivity::class.java
                ).putExtra(DetailUserActivity.USER_KEY, userList[i])
            )
        }

    override fun onBindViewHolder(holder: UserViewHolder, i: Int) {
        val data = userList[i]
        holder.binding.apply {
            tvRowName.text = data.login
            tvRowUsername.text = "ID " + data.id
            Glide.with(ivRowIcon).load(data.avatarUrl).into(ivRowIcon)
        }
    }

    override fun getItemCount(): Int = userList.size
}