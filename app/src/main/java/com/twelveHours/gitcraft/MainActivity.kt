import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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


class MainActivity : AppCompatActivity(), RepoCallback, UserCallback {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdacterVh

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)?.findNavController()

        navView.setupWithNavController(navController!!)

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.usuarioFragment -> {
                    navController.navigate(R.id.usuarioFragment)
                    true
                }
                R.id.principalFragment -> {
                    navController.navigate(R.id.principalFragment)
                    true
                }
                R.id.nuevoFragment -> {
                    navController.navigate(R.id.nuevoFragment)
                    true
                }
                else -> false
            }
        }

        val githubApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubServiceRequest::class.java)

        val username = "EduardJiron"

        val gitUserView = GitUserView()
        gitUserView.getUser(githubApiService, username, this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val gitRepoView = GitRepoView()
        gitRepoView.getRepos(githubApiService, username, this)
    }

    override fun onReposReceived(repos: List<Repository>) {
        adapter = RepoAdacterVh(repos)
        recyclerView.adapter = adapter
    }

    override fun onUserReceived(user: User) {
        val imageView = findViewById<ImageView>(R.id.imageUser)
        val nombreUser = findViewById<TextView>(R.id.nombreUser)
        val seguidos = findViewById<TextView>(R.id.seguidos)
        val seguidores = findViewById<TextView>(R.id.seguidores)

        Glide.with(this).load(user.image).into(imageView)
        nombreUser.text = user.login
        seguidos.text = user.following
        seguidores.text = user.followers
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
