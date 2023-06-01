package com.twelveHours.gitcraft.negocio

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import org.json.JSONObject

class GitRepoAdd {





    fun crearRepositorio(nombre:String,descripcion:String, AUTH_TOKEN:String) {
        val url = "https://api.github.com/user/repos"
        val headers = mapOf("Authorization" to "token $AUTH_TOKEN", "Content-Type" to "application/json")
        val repoData = JSONObject()
        repoData.put("name", "$nombre")
        repoData.put(
            "description",
            "$descripcion"
        )

        Fuel.post(url)
            .header(headers)
            .body(repoData.toString())
            .response { _, response, result ->
                if (response.isSuccessful) {
                    println("Repositorio creado exitosamente")
                } else {
                    println("Error al crear el repositorio: ${response.statusCode}")

                }
            }
    }
}