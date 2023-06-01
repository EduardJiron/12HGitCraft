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

    @Query("Select *from tbl_usuarioscraft")
    fun getAllUser ():List<UsuarioLogin>?

    @Query("SELECT * FROM tbl_usuarioscraft WHERE usuario = :usuario AND password = :password")
    fun getUserByUsernameAndPassword(usuario: String, password: String): UsuarioLogin?
}

