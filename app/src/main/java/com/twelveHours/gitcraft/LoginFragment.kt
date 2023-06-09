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
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.twelveHours.gitcraft.db.UsuarioLoginDatabase
import com.twelveHours.gitcraft.entidad.UsuarioLogin
import androidx.navigation.fragment.findNavController
import com.twelveHours.gitcraft.negocio.UserName


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment() : Fragment() {
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
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        //val usuarioCraft : EditText = view.findViewById(R.id.editTextUsuario)
        //val passwordCraft : EditText = view.findViewById(R.id.editTextPassword)
        val text: Button = view.findViewById(R.id.btnInicar)

        val usuarioCraft : EditText = view.findViewById(R.id.editTextUsuario)
        //Al R.id.editText lo cambié por editTextToken porque también le cambié el id en el .xml
        val passwordCraft : EditText = view.findViewById(R.id.editTextToken)



        text.setOnClickListener {

            //comprobación de que el usuario y la contraseña no estén vacíos
            if (usuarioCraft.text.toString().isEmpty() ) {
                Toast.makeText(requireContext(), "El usuario no puede estar vacio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val fragment = ContainerFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, fragment)
                transaction.addToBackStack(null)
                transaction.commit()

                val user = usuarioCraft.text.toString()
                val userName = usuarioCraft.text.toString()
                val token = passwordCraft.text.toString()

                UserName.setUserName(userName)
                UserName.setToken(token)
            }
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}