package com.fahri.githubusers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahri.githubusers.data.Repo
import com.fahri.githubusers.databinding.RowItemRepoBinding

class RepoAdapter(private val repoList: List<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    inner class RepoViewHolder(val binding: RowItemRepoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder = RepoViewHolder(
        RowItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RepoViewHolder, i: Int) {
        holder.binding.apply {
            tvRowName.text = repoList[i].name
            tvRowDescription.text = repoList[i].description
        }
    }

    override fun getItemCount(): Int = repoList.size
}