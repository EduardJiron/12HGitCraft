package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import okhttp3.Request
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitHubServiceRequest {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>


    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String, @Header("Authorization") authorization: String): Call<List<Repository>>

    @GET("users/{username}/starred")
    fun getStarred(@Path("username") username: String): Call<List<Repository>>

    //numero de repositorios
    @GET("users/{username}")
    fun getNumberRepo(@Path("username") username: String): Call<User>
    @DELETE("repos/{owner}/{repoName}")
    fun deleteRepository(
        @Path("owner") owner: String,
        @Path("repoName") repoName: String
    ): Call<Void>

    @GET("user/repos")
    fun getRepoWithToken(
        @Header("Authorization") token: String
    ): Call<List<Repository>>







}
