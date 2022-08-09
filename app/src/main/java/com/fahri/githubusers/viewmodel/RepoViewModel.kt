package com.fahri.githubusers.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahri.githubusers.data.Repo
import com.fahri.githubusers.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModel: ViewModel() {

    private val listUserRepos = MutableLiveData<List<Repo>>()

    fun getListUserRepos(username: String) {
        ApiConfig.getApiServices().getUserRepos(username).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                listUserRepos.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }
        })
    }
    fun getResultUserRepos(): LiveData<List<Repo>> = listUserRepos
}