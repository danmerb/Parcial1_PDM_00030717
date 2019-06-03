package com.example.parcial1basketball.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parcial1basketball.room.daos.EquipoDao
import com.example.parcial1basketball.room.daos.PartidoDao
import com.example.parcial1basketball.room.entities.Equipo
import com.example.parcial1basketball.room.entities.Partido


@Database(entities = [Partido::class,Equipo::class], version = 2, exportSchema = false)
public abstract class RoomDB: RoomDatabase() {

    abstract fun partidoDao(): PartidoDao
    abstract fun equipoDao(): EquipoDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {
            //val tempInstance = INSTANCE
            if (INSTANCE != null) {
                return INSTANCE!!
            } else {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, RoomDB::class.java, "Partido_Database")
                        .fallbackToDestructiveMigration()
                        .build()
                    return INSTANCE!!
                }
            }
        }

    }

}