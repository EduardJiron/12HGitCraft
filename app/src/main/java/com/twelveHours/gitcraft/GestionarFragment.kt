package com.twelveHours.gitcraft
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.datos.ButtonClick
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.vistas.RepoAdacterVh
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GestionarFragment : Fragment(), RepoCallback, ButtonClick {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdacterVh
    private lateinit var numRepo: TextView
    private lateinit var button: Button
    private lateinit var textView: TextView
    private val handler = Handler()
    private val updateRecyclerViewTask = object : Runnable {
        override fun run() {
            val updatedRepos: List<Repository> = getUpdatedRepos()
            refreshRecyclerView(updatedRepos)
            handler.postDelayed(this, 5000)
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val username = "12HDeveloper"

        recyclerView = view.findViewById(R.id.cardView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val gitRepoView = GitRepoView()
        gitRepoView.getRepoStar(githubApiService, username, this)

        // Inicia la actualización del RecyclerView cada 5 segundos
        handler.postDelayed(updateRecyclerViewTask, 1000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestionar, container, false)
    }

    override fun onReposReceived(repos: List<Repository>, number: Int) {
        adapter = RepoAdacterVh(repos, this)
        textView = view?.findViewById(R.id.repositorios) ?: TextView(context)
        recyclerView.adapter = adapter
    }

    override fun onError(errorMessage: String) {
        println("Error: $errorMessage")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Detiene la actualización del RecyclerView al destruir la vista
        handler.removeCallbacks(updateRecyclerViewTask)
    }

    private fun getUpdatedRepos(): List<Repository> {
        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val username = "12HDeveloper"
        val gitRepoView = GitRepoView()
        val repos: List<Repository> = gitRepoView.getRepoStar(githubApiService, username, this)
        return repos
    }

    override fun onUpdate() {
        val updatedRepos: List<Repository> = getUpdatedRepos()
        refreshRecyclerView(updatedRepos)
    }

    private fun refreshRecyclerView(repos: List<Repository>) {
        adapter = RepoAdacterVh(repos, this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}