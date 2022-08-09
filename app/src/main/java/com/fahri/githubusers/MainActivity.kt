package com.fahri.githubusers

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.fahri.githubusers.adapter.UserAdapter
import com.fahri.githubusers.databinding.ActivityMainBinding
import com.fahri.githubusers.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getResultUserSearch().observe(this) {
            Log.i(TAG, "onCreate: $it")

            binding.rvUsers.apply {
                adapter = UserAdapter(it)
            }
        }

        binding.svUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getListUserSearch(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }
}