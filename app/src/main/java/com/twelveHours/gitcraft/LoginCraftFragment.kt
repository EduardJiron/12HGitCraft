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
import com.twelveHours.gitcraft.CuentaCraftFragment
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.db.UsuarioLoginDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.PrintWriter

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

        return inflater.inflate(R.layout.fragment_login_craft, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = UsuarioLoginDatabase.getInstance(requireContext())

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
            val user = db.usuarioLoginDao().getAllUser()
                // Acceder al DAO para buscar el usuario



            if (user != null) {
                for(it in user){
                //funcional user
                println("${it.password},${it.usuario}, ")
                    Toast.makeText(context, "Ingrese los datos", Toast.LENGTH_SHORT).show()
            }

                // El usuario y contraseña son correctos
                // Realizar la lógica de inicio de sesión exitoso
            } else {
                Toast.makeText(context, "ingrese los datos correctos", Toast.LENGTH_SHORT).show()
                // El usuario y/o contraseña son incorrectos
                // Mostrar mensaje de error o realizar alguna acción
            }

        }
    }
}