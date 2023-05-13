package com.twelveHours.gitcraft.negocio.vistas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.entidad.Repository

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

            textViewNombre.text = repository.name
            textViewDescripcion.text = repository.description?: "No hay descripcion"
            textViewLenguaje.text = repository.language




        }
    }
}