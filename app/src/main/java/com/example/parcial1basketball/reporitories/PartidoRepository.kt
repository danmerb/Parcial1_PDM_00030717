package com.example.parcial1basketball.reporitories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.parcial1basketball.room.daos.EquipoDao
import com.example.parcial1basketball.room.daos.PartidoDao
import com.example.parcial1basketball.room.entities.Equipo
import com.example.parcial1basketball.room.entities.Partido

class PartidoRepository (private val partidoDao: PartidoDao, private val equipoDao: EquipoDao) {


    @WorkerThread
    suspend fun insertPartido(partido: Partido) {
        partidoDao.insert(partido)

    }

    @WorkerThread
    suspend fun insertEquipo(equipo:Equipo) {
        equipoDao.insert(equipo)
    }
    fun getThisMatchId(hora: String, fecha:String):LiveData<Long> {
        return partidoDao.getThisMatchId(hora, fecha)
    }

    fun getAllEquipos(idPartido: Long): LiveData<List<Equipo>> {
        return equipoDao.getAllEquipos(idPartido)
    }
    fun getAllMatches():LiveData<List<Partido>>{
        return partidoDao.getAllMatches()
    }


}