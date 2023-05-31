package com.twelveHours.gitcraft.negocio
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import org.json.JSONObject

class GitRepoUpdate private constructor(private val authToken: String) {

    companion object {
        private var instance: GitRepoUpdate ? = null

        fun getInstance(authToken: String): GitRepoUpdate  {
            if (instance == null) {
                instance = GitRepoUpdate (authToken)
            }
            return instance as GitRepoUpdate
        }
    }

    fun editarRepositorio(nombre: String, nuevoNombre: String, nuevaDescripcion: String) {
        val url = "https://api.github.com/repos/$nombre"
        val headers = mapOf("Authorization" to "token $authToken", "Content-Type" to "application/json")
        val repoData = JSONObject()
        repoData.put("name", nuevoNombre)
        repoData.put("description", nuevaDescripcion)

        Fuel.patch(url)
            .header(headers)
            .body(repoData.toString())
            .response { _, response, result ->
                if (response.isSuccessful) {
                    println("Repositorio editado exitosamente")
                } else {
                    println("Error al editar el repositorio: ${response.statusCode}")
                }
            }
    }
}
