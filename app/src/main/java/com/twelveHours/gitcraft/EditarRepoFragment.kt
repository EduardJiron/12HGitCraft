package com.twelveHours.gitcraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twelveHours.gitcraft.databinding.FragmentEditarRepoBinding
import com.twelveHours.gitcraft.negocio.GitRepoUpdate
import com.twelveHours.gitcraft.negocio.RepoUpdate
import com.twelveHours.gitcraft.negocio.UserName


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditarRepoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentEditarRepoBinding
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

        binding = FragmentEditarRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authToken = UserName.getToken()
        val reponame=RepoUpdate.getUserName()

        val nombre=binding.txtNombre.text.toString()
        val descripcion=binding.txtDesc.toString()
        val gitRepoEdit = GitRepoUpdate.getInstance(authToken)



    }

    companion object {

        fun newInstance() = EditarRepoFragment()

        fun newInstance(param1: String, param2: String) =
            EditarRepoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}