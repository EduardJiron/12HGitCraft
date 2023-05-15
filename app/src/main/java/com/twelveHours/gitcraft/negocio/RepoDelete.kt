package com.twelveHours.gitcraft.negocio

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoDelete {
    private val BASE_URL = "https://api.github.com/"
    private val AUTH_TOKEN = "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"
    private val githubService: GitHubServiceRequest

    init {8
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val requestWithAuthorization: Request = originalRequest.newBuilder()
                    .header("Authorization", "token $AUTH_TOKEN")
                    .build()
                chain.proceed(requestWithAuthorization)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        githubService = retrofit.create(GitHubServiceRequest::class.java)
    }

    fun deleteRepository(owner: String, repoName: String) {
        val call = githubService.deleteRepository(owner, repoName)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                if (response.isSuccessful) {
                    // El repositorio se borró exitosamente
                } else {
                    // Manejar la respuesta de error
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar el error de la solicitud
            }
        })
    }
}