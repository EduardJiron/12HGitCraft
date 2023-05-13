package com.twelveHours.gitcraft.negocio

import android.util.Log
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitUserView {

    fun getUser(githubApiService: GitHubServiceRequest, username: String) {

        githubApiService.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                // si la respuesta es correcta se obtienen los datos del usuario
                if (response.isSuccessful) {
                    // se obtienen los datos del usuario
                    val user = response.body()
                    //si el usuario no datos se le asigna un valor por defecto
                    //envia los datos del usuario a la interfaz
                    val name = user?.User ?: user?.login ?: "Unknown user"
                    val followers = user?.followers ?: "Unknown followers"
                    val following = user?.following ?: "Unknown following"
                    //como la interfaz no es una lista se pasan los paramentros uno por uno
                    Log.d("GithubApi", "User: $name Followers: $followers Following: $following")
                } else {
                    Log.e("GithubApi", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("GithubApi", "Error: ${t.message}")
            }
        })

    }
}