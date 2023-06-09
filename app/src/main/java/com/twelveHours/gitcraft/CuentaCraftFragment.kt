package com.twelveHours.gitcraft


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.twelveHours.gitcraft.db.UsuarioLoginDatabase
import com.twelveHours.gitcraft.entidad.UsuarioLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CuentaCraftFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CuentaCraftFragment : Fragment() {
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

        val usuarioCraft : EditText = view.findViewById(R.id.txtNombreGraft)
        val passwordCraft : EditText = view.findViewById(R.id.editTextPasswordCraft)
        val btnAgregar: Button = view.findViewById(R.id.btnAgregarGraft)
        val cancel : Button =  view.findViewById(R.id.btnCancGraft)
        val room= Room.databaseBuilder(requireContext(), UsuarioLoginDatabase::class.java, "UsuarioLogin").allowMainThreadQueries().build()
        val dao = room.usuarioLoginDao()

        btnAgregar.setOnClickListener {
            try {
                if (usuarioCraft.text.toString().isEmpty() || passwordCraft.text.toString().isEmpty()) {
                    Toast.makeText(context, "Ingrese los datos", Toast.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            dao.insertarReg(UsuarioLogin(usuario = usuarioCraft.text.toString(), password = passwordCraft.text.toString()))
                        }
                        Toast.makeText(context, "Su usuario fue creado exitosamente!", Toast.LENGTH_LONG).show()
                    }
                    dao.getAllUser()?.forEach {
                        println(it)
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error al crear su usuario", Toast.LENGTH_LONG).show()
            }
        }
        cancel.setOnClickListener {
            val fragment = LoginCraftFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cuenta_craft, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CuentaCraftFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CuentaCraftFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}