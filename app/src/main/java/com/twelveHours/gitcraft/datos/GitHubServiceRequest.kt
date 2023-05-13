package com.twelveHours.gitcraft.datos

import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//interface encargada de comunicarse con la api de github
interface GitHubServiceRequest {

    // peticion para obtener los datos del usuario
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    @GET("users/{username}/repos")
    fun getRepo(@Path("username") username: String): Call<List<Repository>>

    // peticion para obtener los seguidores del usuario
    @GET("users/{user}/followers")

    fun getfollo(@Path("user") user: String): Call<List<User>>

    // peticion para obtener los repositorios del usuario

    @GET("users/{username}/repos")
    fun getStarredRepositories(@Path("username") username: String): Call<List<Repository>>
}