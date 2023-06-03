package com.twelveHours.gitcraft.negocio.vistas

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.databinding.ItemrepositoryBinding
import com.twelveHours.gitcraft.datos.FragmentChange
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.GitRepoAdd
import com.twelveHours.gitcraft.negocio.RepoDelete
import com.twelveHours.gitcraft.negocio.RepoUpdate
import com.twelveHours.gitcraft.negocio.UserName
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context

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
            val usuario =UserName.getUserName().trim()
            binding.tvNombre.text = repository.name
            binding.tvDescripcion.text = repository.description ?: "No hay descripcion"
            binding.tvLenguaje.text = repository.language ?: "lenguaje no identificado"
            binding.tvFecha.text = repository.created_at.substring(0, 10).replace("-", "/")
            val url = "https://github.com/$usuario/${repository.name}/archive/refs/heads/master.zip\n"

            binding.tvNombre.setOnClickListener {


println(url)
                val requests = DownloadManager.Request(Uri.parse(url))
                    .setTitle("Descargando ${repository.name}")
                    .setDescription(repository.description)
                    .setDestinationInExternalPublicDir(

                        Environment.DIRECTORY_DOWNLOADS,
                        "${repository.name}.zip"
                    )
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                val context = binding.root.context
                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                downloadManager.enqueue(requests)
    Toast.makeText(binding.root.context, "Descargando repositorio", Toast.LENGTH_SHORT).show()
            }

            binding.button4.setOnClickListener {
                val repoDe = RepoDelete(UserName.getToken())
                repoDe.deleteRepository(usuario, repository.name)


                AlertDialog.Builder(binding.root.context)
                    .setTitle("Eliminar repositorio")
                    .setMessage("¿Estás seguro de eliminar el repositorio?")
                    .setPositiveButton("Si") { _, _ ->
                        repoDe.deleteRepository(usuario, repository.name)
                        btn.onUpdate()
                        Toast.makeText(binding.root.context, "Repositorio eliminado!", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                        Toast.makeText(binding.root.context, "No se eliminó", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }


            binding.button5.setOnClickListener(){
                UserName.setUri("https://github.com/$usuario/${repository.name}/archive/refs/heads/master.zip\n")
                RepoUpdate.setUserName(repository.name)
                fragmentChange.openContainerFragment()
            }

        }
    }
}
