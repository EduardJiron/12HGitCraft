package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubServiceRequest {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>


    @GET("users/{username}/repos")
    fun getRepo(@Path("username") username: String): Call<List<Repository>>

    @GET("users/{username}/starred")
    fun getStarred(@Path("username") username: String): Call<List<Repository>>

    //numero de repositorios
    @GET("users/{username}")
    fun getNumberRepo(@Path("username") username: String): Call<User>


    @GET("repos/{username}/{reponame}/archive/refs/heads/master.zip")
    fun getDownload(@Path("username") username: String, @Path("reponame") reponame: String): Call<Repository>



}
