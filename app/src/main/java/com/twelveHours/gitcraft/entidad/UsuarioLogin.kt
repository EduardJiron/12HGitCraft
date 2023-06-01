package com.twelveHours.gitcraft.entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuarioscraft")
data class UsuarioLogin(
        @PrimaryKey(autoGenerate = true) val idUser:Int =0,
        @ColumnInfo(name = "usuario") val usuario:String ?,
        @ColumnInfo(name = "password") val password: String ?,
        @ColumnInfo(name = "estado") val estado: Int = 0
)