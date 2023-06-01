package com.twelveHours.gitcraft.negocio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class GitRepoUpdate private constructor(private val authToken: String) {

    companion object {
        private var instance: GitRepoUpdate? = null

        fun getInstance(authToken: String): GitRepoUpdate {
            if (instance == null) {
                instance = GitRepoUpdate(authToken)
            }
            return instance as GitRepoUpdate
        }
    }

    fun editarRepositorio(nombre: String, nuevoNombre: String, nuevaDescripcion: String) {
        val url = "https://api.github.com/repos/12HDeveloper/dsssd"
        val headers = mapOf("Authorization" to "token $authToken", "Content-Type" to "application/json")
        val repoData = JSONObject()
        repoData.put("name", nuevoNombre)
        repoData.put("description", nuevaDescripcion)

        val requestBody = repoData.toString().toRequestBody("application/json".toMediaType())

        GlobalScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .patch(requestBody)
                .headers(headers.toHeaders())
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                println("Repositorio editado exitosamente")
            } else {
                println("Error al editar el repositorio: ${response.code}")
            }
        }
    }
}
