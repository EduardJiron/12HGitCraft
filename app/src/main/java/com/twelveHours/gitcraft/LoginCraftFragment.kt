package com.twelveHours.gitcraft
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.twelveHours.gitcraft.db.UsuarioLoginDatabase
import com.twelveHours.gitcraft.entidad.UsuarioLogin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginCraftFragment : Fragment() {
    private lateinit var db: UsuarioLoginDatabase

    private lateinit var usuarioCraft: EditText
    private lateinit var passwordCraft: EditText
    private lateinit var iniciarC: Button
    private lateinit var nuevaCuenta: TextView

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
       db = Room.databaseBuilder(requireContext(), UsuarioLoginDatabase::class.java, "UsuarioLogin").allowMainThreadQueries().build()

        return inflater.inflate(R.layout.fragment_login_craft, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        usuarioCraft = view.findViewById(R.id.editTextUsuario)
        passwordCraft = view.findViewById(R.id.editTextPassword)
        iniciarC = view.findViewById(R.id.btnInicarCraf)
        nuevaCuenta = view.findViewById(R.id.textNuevoInfo)

        iniciarC.setOnClickListener {
            val username = usuarioCraft.text.toString()
            val password = passwordCraft.text.toString()

            // Realizar el login
            login(username, password)
        }

        nuevaCuenta.setOnClickListener {
            // Navegar a la pantalla de creación de cuenta
            val fragment = CuentaCraftFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun login(username: String, password: String) {
        lifecycleScope.launch {
            val user = withContext(Dispatchers.IO) {
                db.usuarioLoginDao().getUserByUsernameAndPassword(username, password)
            }




    //comprovar datos vacios
    if (usuarioCraft.text.isEmpty() || passwordCraft.text.isEmpty()) {
        Toast.makeText(context, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
    }
                else{
        if (user != null) {
            val fragment = LoginFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
            usuarioCraft.setText("")
            passwordCraft.setText("")

        } else {
            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            usuarioCraft.setText("")
            passwordCraft.setText("")

        }
    }
        }
    }

    }


