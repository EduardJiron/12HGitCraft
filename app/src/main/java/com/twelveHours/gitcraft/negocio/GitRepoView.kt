package com.twelveHours.gitcraft.negocio
import android.content.Context
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoView {
    fun getRepos( gitHubServiceRequest: GitHubServiceRequest, username: String, callback: RepoCallback) {
        gitHubServiceRequest.getRepo(username).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    val repos = response.body() ?: emptyList()
                    callback.onReposReceived(repos)
                } else {
                    callback.onError(response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                callback.onError(t.message ?: "Unknown error")
            }
        })
    }
}