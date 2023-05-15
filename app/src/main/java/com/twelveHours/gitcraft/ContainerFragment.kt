package com.twelveHours.gitcraft
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.negocio.GitRepoView
import com.twelveHours.gitcraft.negocio.GitUserView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ContainerFragment : Fragment() {
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
        val navView: BottomNavigationView = view.findViewById(R.id.bottomNavigationView)
        val navController =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView)?.findNavController()

        navView.setupWithNavController(navController!!)

        navView.setOnItemSelectedListener { menuItem ->
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
                R.id.gestionarFragment -> {
                    navController.navigate(R.id.gestionarFragment)


                    true
                }
                else -> false
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}