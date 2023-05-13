package com.twelveHours.gitcraft

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

import com.twelveHours.gitcraft.datos.GitHubServiceRequest
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.entidad.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


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
    }



    }

