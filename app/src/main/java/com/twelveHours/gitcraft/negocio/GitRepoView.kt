package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitRepoView {

    private val cache = mutableMapOf<String, List<Repository>>()

    fun getRepoStar(
        user:String,
        token: String ,
        callback: RepoCallback
    ): List<Repository> {
        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val repos = cache[token] ?: run {
            val authorization = token

            githubApiService.getRepo(user, authorization).enqueue(object : Callback<List<Repository>> {
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