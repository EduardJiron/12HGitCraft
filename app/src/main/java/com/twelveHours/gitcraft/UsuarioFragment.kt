package com.twelveHours.gitcraft
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twelveHours.gitcraft.datos.ButtonClick
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.datos.RepoCallback
import com.twelveHours.gitcraft.datos.UserCallback
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.GitUserView
import com.twelveHours.gitcraft.negocio.vistas.RepoAdacterUserFr
import com.twelveHours.gitcraft.negocio.vistas.RepoAdacterVh
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UsuarioFragment : Fragment(), RepoCallback, UserCallback,ButtonClick {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdacterUserFr
    private lateinit var numRepo: TextView

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val username = "12HDeveloper"
        val gitUserView = GitUserView()
        gitUserView.getUser(githubApiService, username, this)
        recyclerView = view.findViewById(R.id.recyclerView2)
       numRepo = view.findViewById(R.id.numRepo)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val gitRepoView = GitRepoView()
        gitRepoView.getRepoStar(githubApiService, username, this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_usuario, container, false)
    }



    override fun onReposReceived(repos: List<Repository>, number: Int) {

        adapter = RepoAdacterUserFr(repos, this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onUserReceived(user: User) {
        val imageView = view?.findViewById<ImageView>(R.id.imageUser)
        val nombreUser = view?.findViewById<TextView>(R.id.nombreUser)
        val seguidos = view?.findViewById<TextView>(R.id.seguidos)
        val seguidores = view?.findViewById<TextView>(R.id.seguidores)
        val state = view?.findViewById<TextView>(R.id.numRepo)



        if (imageView != null) {
            Glide.with(this).load(user.image).into(imageView)
        }
        if (nombreUser != null) {
            nombreUser.text = user.login
        }
        if (seguidos != null) {
            seguidos.text = user.following
        }
        if (seguidores != null) {
            seguidores.text = user.followers
        }
        if (state != null) {
            state.text = user.public_repos.toString()
        }
    }

    override fun onError(errorMessage: String) {
        println("Error: $errorMessage")
    }

    override fun onUpdate() {

    }
}