package com.example.parcial1basketball.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial1basketball.R
import com.example.parcial1basketball.room.entities.dtos.PartidoXEquipoDTO
import kotlinx.android.synthetic.main.partido_cardview.view.*

class PartidoAdapter internal  constructor(context: Context, val clickListener:(PartidoXEquipoDTO)->Unit ) : RecyclerView.Adapter<PartidoAdapter.ViewHolder>() {
    private var partidos: ArrayList<PartidoXEquipoDTO> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partido_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = partidos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(partidos[position], clickListener)

    internal fun setPartidos(partidos: ArrayList<PartidoXEquipoDTO>) {
        this.partidos = partidos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(partido: PartidoXEquipoDTO, clickListener: (PartidoXEquipoDTO) -> Unit) = with(itemView) {
            tv_name_equipo1.text=partido.eq1
            tv_puntaje_team1.text=partido.eq1Score
            tv_name_equipo2.text=partido.eq2
            tv_puntaje_team2.text=partido.eq2Score
            tv_fecha.text=partido.fecha
            tv_hora.text=partido.hora
            val ganador= if(partido.winner){partido.eq1}else{partido.eq2}
            if(partido.eq1Score==partido.eq2Score){
                tv_nameWinner.text= "Empate"
            }else {
                tv_nameWinner.text = ganador
            }
            this.setOnClickListener(){
                clickListener(partido)
            }
        }
    }
}