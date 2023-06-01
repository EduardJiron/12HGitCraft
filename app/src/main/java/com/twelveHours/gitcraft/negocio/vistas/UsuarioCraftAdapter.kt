package com.twelveHours.gitcraft.negocio.vistas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.entidad.UsuarioLogin

class UsuarioCraftAdapter(private val usuarioCraft : List<UsuarioLogin>): RecyclerView.Adapter<UsuarioCraftAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioCraftAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recycle_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: UsuarioCraftAdapter.ViewHolder, position: Int) {
        holder.bindItems(usuarioCraft[position])
    }

    override fun getItemCount(): Int {
        return usuarioCraft.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(usuarioCraft: UsuarioLogin){
            val textViewUsuario = itemView.findViewById(R.id.tvUsuario) as TextView
            textViewUsuario.text = usuarioCraft.usuario
            val editarUsuario=itemView.findViewById(R.id.btnEditarUserCraft) as TextView


            editarUsuario.setOnClickListener {

            }



        }
    }
}