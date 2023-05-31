package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.UserCallback
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitUserView {
    private val cache = HashMap<String, User>()

    fun getUser(

        username: String,
        callback: UserCallback
    ) {

        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val cachedUser = cache[username]

        if (cachedUser != null) {

            callback.onUserReceived(cachedUser)
            return
        }


        githubApiService.getUser(username)
            .enqueue(
                object : Callback<User> {

                    override fun onResponse(call: Call<User>, response: Response<User>) {

                        if (response.isSuccessful) {

                            val user = response.body()
                            cache[username] = user!!
                            callback.onUserReceived(user)
                        } else {

                            callback.onError("User not found")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        callback.onError("Error: ${t.message}")
                    }
                }
            )
    }
}
