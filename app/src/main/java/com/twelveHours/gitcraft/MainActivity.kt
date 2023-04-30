package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubApiService = retrofit.create(GitHubServiceRequest::class.java)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val username = "EduardJiron"

            githubApiService.getUser(username).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        val name = user?.User ?: user?.login ?: "Unknown user"
                        val followers = user?.followers ?: "Unknown followers"
                        val following = user?.following ?: "Unknown following"

                        Log.d("GithubApi", "Name: $name")
                        Log.d("GithubApi", "Followers: $followers")
                        Log.d("GithubApi", "Following: $following")

                    } else {
                        Log.e("GithubApi", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("GithubApi", "Error: ${t.message}")
                }
            })
            githubApiService.getfollo(username).enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val followers = response.body()
                        followers?.forEach {
                            Log.d("GithubApi", "Followin: ${it.followers}")
                        }
                    } else {
                        Log.e("GithubApi", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("GithubApi", "Error: ${t.message}")
                }
            })
            githubApiService.getStarredRepositories(username).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    if (response.isSuccessful) {
                        val followers = response.body()
                        val url = followers?.get(0)?.url ?: "Unknown url"
                        followers?.forEach {
                            Log.d("GithubApi", "Repositorios: ${it.name}")

                            Log.d("Repo URL:","https://github.com/$username"+"/"+it.name)

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
    }
