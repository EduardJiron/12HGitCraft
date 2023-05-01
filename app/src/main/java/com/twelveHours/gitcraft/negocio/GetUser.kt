package com.twelveHours.gitcraft.negocio

import android.util.Log
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUser {

    fun getUser(githubApiService: GitHubServiceRequest, username: String) {

        githubApiService.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    val name = user?.User ?: user?.login ?: "Unknown user"
                    val followers = user?.followers ?: "Unknown followers"
                    val following = user?.following ?: "Unknown following"

                    println("Name: $name")
                    println("Followers Num: $followers")
                    println("following Num: $following")
                } else {
                    Log.e("GithubApi", "Error: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("GithubApi", "Error: ${t.message}")
            }
        })

        githubApiService.getfollo(username).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {

                    val followers = response.body()

                    followers?.forEach {

                        println("Follower: ${it.login}")
                    }
                } else {
                    Log.e("GithubApi", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("GithubApi", "Error: ${t.message}")
            }
        })
    }
}