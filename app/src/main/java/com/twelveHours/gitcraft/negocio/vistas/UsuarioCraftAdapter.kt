package com.twelveHours.gitcraft.negocio.vistas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twelveHours.gitcraft.GestionUsuarioCraftFragment
import com.twelveHours.gitcraft.R
import com.twelveHours.gitcraft.datos.FragmentChange
import com.twelveHours.gitcraft.entidad.UsuarioLogin
import com.twelveHours.gitcraft.negocio.UserName

class UsuarioCraftAdapter(private val usuarioCraft : List<UsuarioLogin> , val fragmentChange: FragmentChange): RecyclerView.Adapter<UsuarioCraftAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioCraftAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recycle_view, parent, false)
        return ViewHolder(v, fragmentChange)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(usuarioCraft[position])
    }



    override fun getItemCount(): Int {
        return usuarioCraft.size
    }

    class ViewHolder(itemView: View, val fragmentChange: FragmentChange): RecyclerView.ViewHolder(itemView){
        fun bindItems(usuarioCraft: UsuarioLogin){
            val textViewUsuario = itemView.findViewById(R.id.tvUsuario) as TextView
            textViewUsuario.text = usuarioCraft.usuario
            val editarUsuario=itemView.findViewById(R.id.btnEditarUserCraft) as TextView


            editarUsuario.setOnClickListener {
            UserName.setId(usuarioCraft.idUser)
                fragmentChange.openEditFragment()
            }



        }
    }
}