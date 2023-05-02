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

// clase principal se agregan las interfaces de las clases que se encargan de obtener los datos(negocio/GetUser,GetRepo)
class MainActivity : AppCompatActivity(), GetUserListener, GetRepoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //acces a la api de github
        val githubApiService = Retrofit.Builder()
                //endepoint de la api
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                                //token de acceso a la api 5000 request por hora
                            .addHeader("Authorization", "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")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
                // se crea la instancia de la api
            .create(GitHubServiceRequest::class.java)
        //se crea la instancia de la clase que se encarga de obtener los datos (negocio/GetUser)
        val gUSer= GetUser()
        //se crea la instancia de la clase que se encarga de obtener los datos (negocio/GetRepo)
        val gRepo= GetRepo()


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //se obtiene el usuario , es opcional con el token basta
            val username = "EduardJiron"

            //se llama a los metodos de las clases que se encargan de obtener los datos(negocio/GetUser)
            gUSer.getUser(githubApiService, username, this)
            //se llama a los metodos de las clases que se encargan de obtener los datos(negocio/GetRepo)
            gRepo.getRepos(githubApiService, username, this)
        }
        }

    //se implementan los metodos de las interfaces de las clases que se encargan de obtener los datos(negocio/GetUser)
    override fun onUserLoaded(name: String, followers: String, following: String) {
        val txtUser: TextView = findViewById(R.id.txtUser)
        //como solo es un user no se usa string builder ni ciclos
        txtUser.text = "Name: $name\nFollowers Num: $followers\nFollowing Num: $following"
    }
    //se implementan los metodos de las interfaces de las clases que se encargan de obtener los datos(negocio/GetRepo)
    override fun onRepoLoaded(repository: List<Repository>) {
        //se crea un string builder para mostrar los datos sin el string build el textbox no puede imprimir mas de un elemento
        val textRepo: TextView = findViewById(R.id.txtRepo)
        val sb = StringBuilder()
        //se recorre la lista de repositorios y se agregan al string builder
        for (repo in repository) {
            //se agrega el nombre y la url del repositorio
            //ojo el url no es funcional voy a tener que crear una forma de unir el user con el repo
            sb.append("${repo.name}\n ${repo.url}\n\n")
            //se imprime el string builder en el textbox
            textRepo.text = sb.toString()
        }

    }


}
