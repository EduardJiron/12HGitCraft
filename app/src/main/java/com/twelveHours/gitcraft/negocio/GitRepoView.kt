package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoView {

    private val cache = mutableMapOf<String, List<Repository>>()

    fun getRepoStar(
        gitHubServiceRequest: GitHubServiceRequest,
        token: String,
        user: String,
        callback: RepoCallback
    ): List<Repository> {
        val repos = cache[token] ?: run {
            val authorization = "Bearer $token"
            gitHubServiceRequest.getRepo(user, authorization).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        val newRepos = response.body() ?: emptyList()
                        cache[token] = newRepos
                        callback.onReposReceived(newRepos, newRepos.size)
                    } else {
                        callback.onError(response.code().toString())
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    callback.onError(t.message ?: "Unknown error")
                }
            })
            emptyList()
        }

        return repos
    }

}