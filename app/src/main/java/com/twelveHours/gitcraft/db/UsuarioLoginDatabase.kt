package com.twelveHours.gitcraft.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twelveHours.gitcraft.dao.UsuarioLoginDao
import com.twelveHours.gitcraft.entidad.UsuarioLogin

@Database(entities = [UsuarioLogin::class], version =1, exportSchema = false )
abstract class UsuarioLoginDatabase : RoomDatabase() {

    abstract fun usuarioLoginDao(): UsuarioLoginDao

    companion object{
        @Volatile
        private var INSTANCE: UsuarioLoginDatabase?= null

        fun obtBaseDatos(context: Context):UsuarioLoginDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsuarioLoginDatabase::class.java,
                    "gitcraft"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}