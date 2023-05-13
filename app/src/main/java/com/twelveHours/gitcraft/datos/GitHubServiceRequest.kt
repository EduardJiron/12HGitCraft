package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubServiceRequest {

    @GET("users/{username}") fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/repos")
    fun getRepo(@Path("username") username: String): Call<List<Repository>>
}
