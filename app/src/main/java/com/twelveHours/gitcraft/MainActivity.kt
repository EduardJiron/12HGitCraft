package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.GitUserView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(){
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
            val callU = GitUserView()
            val callR = GitRepoView()

            button.setOnClickListener(){
                callU.getUser(githubApiService, username)
                callR.getRepos(githubApiService, username)
            }



        }



}
    }



