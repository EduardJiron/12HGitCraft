package com.twelveHours.gitcraft


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.datos.UserCallback
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.GitUserView

import com.twelveHours.gitcraft.negocio.vistas.RepoAdacterVh

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity: AppCompatActivity(), RepoCallback,UserCallback {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdacterVh

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val username = "EduardJiron"


        val imageView = findViewById<ImageView>(R.id.imageUser)



        val gitUserView = GitUserView()
        gitUserView.getUser(githubApiService, username, this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val gitRepoView = GitRepoView()
        gitRepoView.getRepos( githubApiService, username, this)


    }



    override fun onReposReceived(repos: List<Repository>) {
        adapter = RepoAdacterVh(repos)
        recyclerView.adapter = adapter
    }

    override fun onReposReceived(repos: User) {
        val imageView = findViewById<ImageView>(R.id.imageUser)
        val nombreUser= findViewById<TextView>(R.id.nombreUser)
        val seguidos= findViewById<TextView>(R.id.seguidos)
        val seguidores= findViewById<TextView>(R.id.seguidores)

        Glide.with(this).load(repos.image).into(imageView)
        nombreUser.text=repos.login
        seguidos.text=repos.following
        seguidores.text=repos.followers


    }


    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }



}




