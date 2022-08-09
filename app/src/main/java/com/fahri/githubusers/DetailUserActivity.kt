package com.fahri.githubusers

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fahri.githubusers.adapter.RepoAdapter
import com.fahri.githubusers.data.Repo
import com.fahri.githubusers.data.User
import com.fahri.githubusers.databinding.ActivityDetailUserBinding
import com.fahri.githubusers.viewmodel.RepoViewModel
import com.fahri.githubusers.viewmodel.UserViewModel

class DetailUserActivity : AppCompatActivity() {

    private var _binding : ActivityDetailUserBinding? = null
    private val binding get() = _binding as ActivityDetailUserBinding

    private var _user: User? = null
    private val user get() = _user as User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _user = intent.getParcelableExtra(USER_KEY)

        val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        user.login?.let { userViewModel.getDetailUser(it) }
        // Property Bind
        userViewModel.getResultDetailUser().observe(this) {
            binding.apply {
                Glide.with(ivDetailUser).load(it.avatarUrl).into(ivDetailUser)
                tvDetailName.text = it.login
                tvDetailUsername.text = it.name
                tvDetailUserDesc.text = it.bio ?: "Bio Not Addressed"
                tvDetailFollowers.text = "Followers :" + it.followers
                tvDetailFollowing.text = "Following :" + it.following
                tvDetailReposAmount.text = "Repo :" + it.public_repos

            }
        }

        val repoViewModel = ViewModelProvider(this)[RepoViewModel::class.java]
        user.login?.let { repoViewModel.getListUserRepos(it) }
        // Recycler View Repos
        repoViewModel.getResultUserRepos().observe(this) {
            binding.rvDetailRepo.adapter = RepoAdapter(it)
        }

    }

    companion object {
        const val USER_KEY = "USER_KEY"
    }
}
