package com.twelveHours.gitcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.twelveHours.gitcraft.datos.ButtonClick
import com.twelveHours.gitcraft.db.UsuarioLoginDatabase


class MainActivity : AppCompatActivity() {
    lateinit var appDatabase: UsuarioLoginDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(this, UsuarioLoginDatabase::class.java, "UsuarioLogin").allowMainThreadQueries().build()

        val loginFragment = LoginCraftFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, loginFragment)
        transaction.commit()


    }




}







