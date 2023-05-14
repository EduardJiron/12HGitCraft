package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() /* RepoCallback, UserCallback*/ {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)?.findNavController()

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
                R.id.usuarioFragment -> {
                    navController.navigate(R.id.usuarioFragment)


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
}
