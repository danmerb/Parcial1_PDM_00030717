package com.example.parcial1basketball.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial1basketball.R
import com.example.parcial1basketball.adapters.PartidoAdapter
import com.example.parcial1basketball.room.entities.dtos.PartidoXEquipoDTO
import com.example.parcial1basketball.viewModels.PartidoViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listDTO: ArrayList<PartidoXEquipoDTO> = ArrayList()
    lateinit var partidoAdapter: PartidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModelPartidoEquipo = ViewModelProviders.of(this).get(PartidoViewModel::class.java)
        partidoAdapter = PartidoAdapter(this, {partido:PartidoXEquipoDTO-> iniciarActivityPartido(partido)})
        rvPartidos.apply {
            adapter = partidoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModelPartidoEquipo.getAllPartidos().observe(this, Observer { listaPartidos ->
            if (listaPartidos.size != 0) {
                listDTO.clear()
                listaPartidos.forEach { partido ->
                    viewModelPartidoEquipo.getAllEquipos(partido.id_partido).observe(this@MainActivity, Observer { listaEquipos ->
                        if (listaEquipos.size >= 2) {
                            listDTO.add(PartidoXEquipoDTO( listaEquipos[1].equipo_name, listaEquipos[0].equipo_name, listaEquipos[0].equipo_puntaje, listaEquipos[1].equipo_puntaje,partido.partido_hora, partido.partido_fecha, listaEquipos[0].equipo_isWinner))
                        }
                    })
                }
                partidoAdapter.setPartidos(listDTO)
            }
        })

        fab.setOnClickListener() {
            val intent = Intent(this,addMatchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarActivityPartido(partido: PartidoXEquipoDTO) {
        val partidoBundle = Bundle()
        partidoBundle.putParcelable("Match", partido)
        startActivity(Intent(this, MatchActivityView::class.java).putExtra("bundle", partidoBundle))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
