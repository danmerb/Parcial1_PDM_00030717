package com.example.parcial1basketball.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial1basketball.reporitories.PartidoRepository
import com.example.parcial1basketball.room.RoomDB
import com.example.parcial1basketball.room.daos.EquipoDao
import com.example.parcial1basketball.room.entities.Equipo
import com.example.parcial1basketball.room.entities.Partido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidoViewModel(private val context:Application):AndroidViewModel(context) {

    private val repository: PartidoRepository
    init {
        val partidoDao = RoomDB.getInstance(context).partidoDao()
        val equipoDao = RoomDB.getInstance(context).equipoDao()
        repository = PartidoRepository(partidoDao, equipoDao)
    }

     fun insertMatch(partido: Partido) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPartido(partido)
    }

    fun insertEquipo(equipo: Equipo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertEquipo(equipo)
    }
    fun getThisPartidoId(hora: String, fecha:String): LiveData<Long> {
        return  repository.getThisMatchId(hora, fecha)
    }
    fun getAllEquipos(idPartido: Long): LiveData<List<Equipo>>{
        return repository.getAllEquipos(idPartido)
    }
    fun getAllPartidos(): LiveData<List<Partido>> {
        return repository.getAllMatches()
    }
}