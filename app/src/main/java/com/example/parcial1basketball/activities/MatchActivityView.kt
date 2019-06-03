package com.example.parcial1basketball.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.parcial1basketball.R
import com.example.parcial1basketball.room.entities.dtos.PartidoXEquipoDTO
import kotlinx.android.synthetic.main.activity_match_view.*
import kotlinx.android.synthetic.main.activity_match_view.tv_fecha
import kotlinx.android.synthetic.main.activity_match_view.tv_hora
import kotlinx.android.synthetic.main.activity_match_view.tv_nameWinner
import kotlinx.android.synthetic.main.partido_cardview.*

class MatchActivityView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_view)
        val partido: PartidoXEquipoDTO? = intent.getBundleExtra("bundle").getParcelable("Match")
        if(partido!=null){ init(partido)}
    }


    private fun init(partido: PartidoXEquipoDTO?) {
        with(this){

            tv_eq1.text=partido!!.eq1
            tv_eq2.text=partido.eq2
            tv_eq1Score.text=partido.eq1Score
            tv_eq2Score.text=partido.eq2Score
            tv_hora.text=partido!!.hora
            tv_fecha.text=partido!!.fecha

            val ganador= if(partido.winner){partido.eq1}else{partido.eq2}
            if(partido.eq1Score==partido.eq2Score){
                tv_nameWinner.text= "Empate!!"
            }else {
                tv_nameWinner.text = ganador
            }
        }
    }

}
