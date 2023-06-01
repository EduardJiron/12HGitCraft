package com.twelveHours.gitcraft.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twelveHours.gitcraft.entidad.User
import com.twelveHours.gitcraft.entidad.UsuarioLogin
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioLoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertarReg(usuarioLogin: UsuarioLogin)

    @Query("SELECT * FROM tbl_usuarioscraft WHERE id= :id")
    fun obtRegistro(id:Int): Flow<UsuarioLogin>

    @Query("UPDATE tbl_usuarioscraft SET usuario = :usuario, password = :password WHERE id = :id")
    fun update(id: Int, usuario: String, password: String)
}

