package com.example.parcial1basketball.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial1basketball.room.entities.Partido

@Dao
interface PartidoDao {

    //@Query("SELECT*FROM partido_table")
    //fun getAllPartidos(): LiveData<List<Partido>>

    @Query("SELECT partido_id from partido_table WHERE partido_hora=:hora AND partido_fecha=:fecha")
    fun getThisMatchId(hora:String, fecha:String): LiveData<Long>

    @Query("SELECT * FROM partido_table")
    fun getAllMatches(): LiveData<List<Partido>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partido:Partido):Long

    @Query("DELETE FROM partido_table")
    suspend fun deleteAll()
}