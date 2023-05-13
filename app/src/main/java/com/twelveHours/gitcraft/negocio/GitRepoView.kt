package com.twelveHours.gitcraft.negocio
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoView {
    fun getRepos(gitHubServiceRequest: GitHubServiceRequest, username: String) {
        gitHubServiceRequest.getRepo(username).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    val repos = response.body()
                    if (repos != null) {
                        for (repo in repos) {
                            val name = repo.name ?: "Unknown name"
                            val description = repo.description ?: "Unknown description"
                            val language = repo.language ?: "Unknown language"

                            println("Repository: $name")
                            println("Description: $description")
                            println("Language: $language")
                            println("------------------------------------")
                        }
                    } else {
                        println("No repositories found")
                    }
                } else {
                    println("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }



    }
