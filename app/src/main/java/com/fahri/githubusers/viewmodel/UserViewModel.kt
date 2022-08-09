package com.fahri.githubusers.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahri.githubusers.data.User
import com.fahri.githubusers.data.UserSearch
import com.fahri.githubusers.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {

    private val listUserSearch = MutableLiveData<List<User>>()
    private val detailUser = MutableLiveData<User>()

    fun getListUserSearch(query: String) {
        ApiConfig.getApiServices().searchUser(query).enqueue(object : Callback<UserSearch> {
            override fun onResponse(call: Call<UserSearch>, response: Response<UserSearch>) {
                listUserSearch.postValue(response.body()?.items as List<User>?)
            }

            override fun onFailure(call: Call<UserSearch>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }

        })
    }
    fun getResultUserSearch(): LiveData<List<User>> = listUserSearch

    fun getDetailUser(path: String) {
        ApiConfig.getApiServices().getUserDetail(path).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                detailUser.postValue(response.body())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
            }

        })
    }
    fun getResultDetailUser(): LiveData<User> = detailUser
}