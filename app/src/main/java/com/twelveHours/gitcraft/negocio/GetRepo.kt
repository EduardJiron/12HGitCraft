package com.twelveHours.gitcraft.negocio

import android.annotation.SuppressLint
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import android.util.Log
import com.twelveHours.gitcraft.datos.GetRepoListener
import com.twelveHours.gitcraft.entidad.Repository


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// clase encargada de obtener los repositorios
class GetRepo {

    @SuppressLint("NotConstructor")

            // funcion que obtiene los repositorios del usuario
    // los parametros son la interfaz de la api de github, el nombre del usuario y la interfaz que se ejecuta cuando se obtienen los repositorios
    //al haber mas de un repositorio se utiliza una lista
        fun getRepos(githubApiService: GitHubServiceRequest, username: String, listener: GetRepoListener) {
            githubApiService.getStarredRepositories(username).enqueue(object : Callback<List<Repository>> {
                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    // si la respuesta es correcta se obtienen los repositorios
                    if (response.isSuccessful) {
                        // se obtienen los repositorios
                        val repositories = response.body()
                        //envia los datos de los repositorios a la interfaz
                        repositories?.let {
                            // se ejecuta la funcion onRepoLoaded de la interfaz
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