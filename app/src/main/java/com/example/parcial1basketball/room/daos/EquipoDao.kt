package com.example.parcial1basketball.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial1basketball.room.entities.Equipo

@Dao
interface EquipoDao {

    //@Query("SELECT*FROM equipo_table")
    //fun getAllRepos(): LiveData<List<Equipo>>

    @Query("SELECT * FROM  equipo_table  LEFT JOIN partido_table  ON partido_table.partido_id=equipo_table.partido_fk WHERE partido_table.partido_id=:idPartido")
    fun getAllEquipos(idPartido: Long): LiveData<List<Equipo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equipo:Equipo)

    @Query("DELETE FROM equipo_table")
    suspend fun deleteAll()
}