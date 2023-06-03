package com.twelveHours.gitcraft.negocio.vistas

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.databinding.ItemuserrepoBinding
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.RepoDelete
import com.twelveHours.gitcraft.negocio.UserName

class RepoAdacterUserFr(
    private var repository: List<Repository>,
    private val btn: com.twelveHours.gitcraft.datos.ButtonClick
) : RecyclerView.Adapter<RepoAdacterUserFr.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemuserrepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, btn)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repository[position])
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    class ViewHolder(
        private val binding: ItemuserrepoBinding,
        private val btn: com.twelveHours.gitcraft.datos.ButtonClick
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository) {
            val usuario = UserName.getUserName()
            binding.tvNombre.text = repository.name
            binding.tvDescripcion.text = repository.description ?: "No hay descripcion"
            binding.tvLenguaje.text = repository.language ?: "lenguaje no identificado"
            binding.tvFecha.text = repository.created_at.substring(0, 10).replace("-", "/")

            binding.tvNombre.setOnClickListener {
                val clipboard = it.context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("Copied Text", repository.clone_url)
                clipboard.setPrimaryClip(clip)

                Toast.makeText(it.context, "Url copiado al portapapeles", Toast.LENGTH_SHORT).show()

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${usuario}/${repository.name}/archive/refs/heads/master.zip\n"))
                it.context.startActivity(intent)
            }

        }
    }
}