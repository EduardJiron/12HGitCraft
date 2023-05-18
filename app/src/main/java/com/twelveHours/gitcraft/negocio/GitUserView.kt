package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.UserCallback
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitUserView {
    private val cache = HashMap<String, User>()

    fun getUser(
        gitHubServiceRequest: GitHubServiceRequest,
        username: String,
        callback: UserCallback
    ) {
        // Check if the user is already in the cache
        val cachedUser = cache[username]

        if (cachedUser != null) {
            // User is in the cache, return it
            callback.onUserReceived(cachedUser)
            return
        }

        // User is not in the cache, fetch it from the GitHub API
        gitHubServiceRequest.getUser(username)
            .enqueue(
                object : Callback<User> {

                    override fun onResponse(call: Call<User>, response: Response<User>) {

                        if (response.isSuccessful) {
                            // User was found, add it to the cache
                            val user = response.body()
                            cache[username] = user!!
                            callback.onUserReceived(user)
                        } else {
                            // User was not found, error
                            callback.onError("User not found")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        // Error fetching user, error
                        callback.onError("Error: ${t.message}")
                    }
                }
            )
    }
}
