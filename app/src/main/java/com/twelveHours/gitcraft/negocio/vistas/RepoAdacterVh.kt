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
import com.twelveHours.gitcraft.entidad.Repository
import com.twelveHours.gitcraft.negocio.UserName

class RepoAdacterVh(private val repository:List<Repository>): RecyclerView.Adapter<RepoAdacterVh.ViewHolder>( ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrepository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repository[position])
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: Repository) {

         val textViewNombre = itemView.findViewById<TextView>(R.id.tvNombre)
            val textViewDescripcion = itemView.findViewById<TextView>(R.id.tvDescripcion)
            val textViewLenguaje = itemView.findViewById<TextView>(R.id.tvLenguaje)
            val textViewFecha = itemView.findViewById<TextView>(R.id.tvFecha)
            val usuario= UserName().getUserName()
            textViewNombre.text = repository.name
            textViewDescripcion.text = repository.description?: "No hay descripcion"
            textViewLenguaje.text = repository.language?: "lenguaje no identificado"
            textViewFecha.text = repository.created_at.substring(0,10).replace("-","/")



            textViewNombre.setOnClickListener(){


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