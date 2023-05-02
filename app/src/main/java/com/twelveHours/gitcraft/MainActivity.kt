package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.twelveHours.gitcraft.datos.GetRepoListener
import com.twelveHours.gitcraft.datos.GetUserListener
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.GetRepo
import com.twelveHours.gitcraft.negocio.GetUser
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), GetUserListener, GetRepoListener {
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
        val gRepo= GetRepo()


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val username = "EduardJiron"
            gUSer.getUser(githubApiService, username, this)
            for (i in 0..10) {
                gRepo.getRepos(githubApiService, username, this)
            }


        }
        }
    override fun onUserLoaded(name: String, followers: String, following: String) {
        val txtUser: TextView = findViewById(R.id.txtUser)

        txtUser.text = "Name: $name\nFollowers Num: $followers\nFollowing Num: $following"
    }
    override fun onRepoLoaded(repository: List<Repository>) {
        val textRepo: TextView = findViewById(R.id.txtRepo)
        val sb = StringBuilder()

        for (repo in repository) {
            sb.append("${repo.name}\n ${repo.url}\n\n")
            textRepo.text = sb.toString()
        }

    }


}
