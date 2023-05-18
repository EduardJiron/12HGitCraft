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
        gitHubServiceRequest: GitHubServiceRequest,
        username: String,
        callback: RepoCallback
    ): List<Repository> {
        val repos = cache[username] ?: run {
            gitHubServiceRequest.getRepo(username).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        val newRepos = response.body() ?: emptyList()
                        cache[username] = newRepos
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
