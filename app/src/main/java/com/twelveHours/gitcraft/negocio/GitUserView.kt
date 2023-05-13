package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.entidad.User
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.UserCallback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitUserView {

    fun getUser(gitHubServiceRequest: GitHubServiceRequest, username: String, callback: UserCallback) {
        gitHubServiceRequest.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    val avatarUrl = user?.image
                    val name = user?.User

                    if (user != null) {
                        callback.onReposReceived(user)
                        println("aquiiiiiiiiiiiiiiiiiii"+user.image)
                        println(    "aquiiiiiiiiiiiiiiiiiii"+user.login)

                    } else {
                        callback.onError("User not found")
                    }
                } else {
                    callback.onError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onError("Error: ${t.message}")
            }
        })
    }
}


