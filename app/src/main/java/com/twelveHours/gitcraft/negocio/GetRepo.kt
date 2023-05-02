package com.twelveHours.gitcraft.negocio

import android.annotation.SuppressLint
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import android.util.Log
import com.twelveHours.gitcraft.datos.GetRepoListener
import com.twelveHours.gitcraft.entidad.Repository


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetRepo {

    @SuppressLint("NotConstructor")


        fun getRepos(githubApiService: GitHubServiceRequest, username: String, listener: GetRepoListener) {
            githubApiService.getStarredRepositories(username).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    if (response.isSuccessful) {
                        val repositories = response.body()
                        repositories?.let {
                            listener.onRepoLoaded(it)
                        }
                    } else {
                        Log.e("GithubApi", "Error: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Log.e("GithubApi", "Error: ${t.message}")
                }
            })
        }
}