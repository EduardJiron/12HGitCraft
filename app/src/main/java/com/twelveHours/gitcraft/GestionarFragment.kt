package com.twelveHours.gitcraft
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.datos.ButtonClick
import com.twelveHours.gitcraft.datos.FragmentChange
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.UserName
import com.twelveHours.gitcraft.negocio.vistas.RepoAdacterVh
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GestionarFragment : Fragment(), RepoCallback, ButtonClick,FragmentChange {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdacterVh
    private lateinit var btnAdd: Button
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




        recyclerView = view.findViewById(R.id.cardView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val gitRepoView = GitRepoView()
        val user = UserName.getUserName()
        val token = UserName.getToken()
        gitRepoView.getRepoStar( user, token, this)

        btnAdd = view.findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val fragment = AgregarRepoFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        handler.postDelayed(updateRecyclerViewTask, 200000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestionar, container, false)
    }

    override fun onReposReceived(repos: List<Repository>, number: Int) {
        adapter = RepoAdacterVh(repos, this,this)
        textView = view?.findViewById(R.id.repositorios) ?: TextView(context)
        recyclerView.adapter = adapter
    }

    override fun onError(errorMessage: String) {
        println("Error: $errorMessage")
        Toast.makeText(context, "La vida no vale nada", Toast.LENGTH_LONG).show()
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



        val gitRepoView = GitRepoView()
        val user = UserName.getUserName()
        val token = UserName.getToken()
        val repos: List<Repository> = gitRepoView.getRepoStar(user,token,this)
        return repos
    }

    override fun onUpdate() {
        val updatedRepos: List<Repository> = getUpdatedRepos()
        refreshRecyclerView(updatedRepos)
    }

    private fun refreshRecyclerView(repos: List<Repository>) {
        adapter = RepoAdacterVh(repos, this,this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun openContainerFragment() {


        val fragment = EditarRepoFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun openEditFragment() {

    }
}