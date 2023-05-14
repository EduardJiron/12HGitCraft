package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoView {
    private val cache = HashMap<String, List<Repository>>()



    fun getRepoStar(

        gitHubServiceRequest: GitHubServiceRequest,
        username: String,
        callback: RepoCallback
    )
    {

        val cachedRepos = cache[username]

        if (cachedRepos != null) {
            callback.onReposReceived(cachedRepos,cachedRepos.size)

            return
        }



        gitHubServiceRequest

            .getStarred(username)
            .enqueue(
                object : Callback<List<Repository>> {

                    override fun onResponse(
                        call: Call<List<Repository>>,
                        response: Response<List<Repository>>

                    ) {
                        if (response.isSuccessful) {
                            val repos = response.body() ?: emptyList()

                            cache[username] = repos
                            callback.onReposReceived(repos,repos.size)
                        } else {
                            callback.onError(response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                        callback.onError(t.message ?: "Unknown error")
                    }
                }
            )
    }
}