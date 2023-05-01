package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.negocio.GetRepo
import com.twelveHours.gitcraft.negocio.GetUser
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gUSer= GetUser()
        val gRepo= GetRepo()
        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "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")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
            .create(GitHubServiceRequest::class.java)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val username = "EduardJiron"

            Log.d("GithubApi", "Usuarios")
            gUSer.getUser(githubApiService,username)
            Log.d("GithubApi", "Repositorios")
            gRepo.getRepo(githubApiService,username)





        }

        }
    }
