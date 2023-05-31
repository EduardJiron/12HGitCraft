package com.twelveHours.gitcraft.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.twelveHours.gitcraft.entidad.UsuarioLogin

@Dao
interface UsuarioLoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarReg(usuarioLogin: UsuarioLogin)
}