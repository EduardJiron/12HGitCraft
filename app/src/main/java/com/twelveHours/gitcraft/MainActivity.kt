package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.twelveHours.gitcraft.datos.GetUserListener
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.negocio.GetRepo
import com.twelveHours.gitcraft.negocio.GetUser
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), GetUserListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        val gUSer= GetUser()


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val username = "EduardJiron"
            gUSer.getUser(githubApiService, username, this)

        }
        }
    override fun onUserLoaded(name: String, followers: String, following: String) {
        val txtUser: TextView = findViewById(R.id.txtUser)
        txtUser.text = "Name: $name\nFollowers Num: $followers\nFollowing Num: $following"
    }


    }
