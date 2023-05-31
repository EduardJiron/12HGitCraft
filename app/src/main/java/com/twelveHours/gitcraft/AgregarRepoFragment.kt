package com.twelveHours.gitcraft

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.twelveHours.gitcraft.databinding.FragmentAgregarRepoBinding
import com.twelveHours.gitcraft.negocio.GitRepoAdd
import com.twelveHours.gitcraft.negocio.UserName

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AgregarRepoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAgregarRepoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_agregar_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAgregarRepoBinding.bind(view)



        binding.btnAgregar.setOnClickListener(){
            val gitRepoAdd = GitRepoAdd()
            val nombre = binding.txtNombre.text.toString()
            val descripcion = binding.txtDesc.text.toString()
            val token= UserName.getToken()


            AlertDialog.Builder(binding.root.context)
                .setTitle("Agregar repositorio")
                .setMessage("¿Estás seguro de crear el repositorio?")
                .setPositiveButton("si") { _, _ ->
                    gitRepoAdd.crearRepositorio(nombre, descripcion,token)

                    Toast.makeText(context, "Repositorio creado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("no") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(binding.root.context, "No se creó", Toast.LENGTH_SHORT).show()
                }
                .show()


        }

    }
    companion object {



        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AgregarRepoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}