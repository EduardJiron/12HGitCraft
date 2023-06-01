package com.twelveHours.gitcraft.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twelveHours.gitcraft.entidad.User
import com.twelveHours.gitcraft.entidad.UsuarioLogin

@Dao
interface UsuarioLoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarReg(usuarioLogin: UsuarioLogin)

    @Query("SELECT * FROM tbl_usuarioscraft WHERE usuario = :usuario AND password = :password AND estado = :estado")
    fun getUser(usuario: String, password: String, estado :Int): UsuarioLogin?
}

