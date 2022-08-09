package com.fahri.githubusers.network

import com.fahri.githubusers.data.Repo
import com.fahri.githubusers.data.User
import com.fahri.githubusers.data.UserSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser(
        @Query("q") username : String
    ) : Call<UserSearch>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ) : Call<User>

    @GET("users/{username}/repos")
    fun getUserRepos(
        @Path("username") username: String
    ) : Call<List<Repo>>
}