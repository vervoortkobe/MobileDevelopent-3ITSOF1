package edu.ap.githubuser.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<UserResponse>
}