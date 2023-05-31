package com.twelveHours.gitcraft.negocio.vistas

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.databinding.ItemrepositoryBinding
import com.twelveHours.gitcraft.datos.FragmentChange
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.RepoDelete
import com.twelveHours.gitcraft.negocio.UserName

class RepoAdacterVh(
    private var repository: List<Repository>,
    private val btn: com.twelveHours.gitcraft.datos.ButtonClick,
    val fragmentChange: FragmentChange
) : RecyclerView.Adapter<RepoAdacterVh.ViewHolder>() {

    fun updateData(newRepos: List<Repository>) {
        repository = newRepos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemrepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, btn, fragmentChange)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repository[position])
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    class ViewHolder(
        private val binding: ItemrepositoryBinding,
        private val btn: com.twelveHours.gitcraft.datos.ButtonClick,
        val fragmentChange: FragmentChange
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository) {
            val usuario = UserName().getUserName()
            binding.tvNombre.text = repository.name
            binding.tvDescripcion.text = repository.description ?: "No hay descripcion"
            binding.tvLenguaje.text = repository.language ?: "lenguaje no identificado"
            binding.tvFecha.text = repository.created_at.substring(0, 10).replace("-", "/")

            binding.tvNombre.setOnClickListener {




                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${usuario}/${repository.name}/archive/refs/heads/master.zip\n"))
                it.context.startActivity(intent)
            }

            binding.button4.setOnClickListener {
                val repoDe = RepoDelete()
                repoDe.deleteRepository(usuario, repository.name)
                btn.onUpdate()

                Toast.makeText(it.context, "Repositorio eliminado", Toast.LENGTH_SHORT).show()

            }

            binding.button5.setOnClickListener(){

            fragmentChange.openContainerFragment()
            }
        }
    }
}