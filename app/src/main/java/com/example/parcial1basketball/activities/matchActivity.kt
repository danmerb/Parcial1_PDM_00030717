package com.example.parcial1basketball.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.example.parcial1basketball.R
import com.example.parcial1basketball.room.entities.Equipo
import com.example.parcial1basketball.room.entities.Partido
import com.example.parcial1basketball.viewModels.PartidoViewModel
import kotlinx.android.synthetic.main.activity_match.*

class matchActivity : AppCompatActivity() {

    lateinit var hora: String
    lateinit var fecha: String
    lateinit var Eq1Name:String
    lateinit var Eq2Name:String
    var partidoId:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val partido: Partido? = intent.getBundleExtra("Match").getParcelable("partido")
        if(partido!=null) {
            hora = partido.partido_hora
            fecha = partido.partido_fecha
        }

        Eq1Name=intent.getStringExtra("team_eq1")
        Eq2Name=intent.getStringExtra("team_eq2")
        tv_fechapartido.text=fecha
        tv_horapartido.text=hora
        tv_team1.text=Eq1Name
        tv_team2.text=Eq2Name
        val eq1Score = ViewModelProviders.of(this).get(contadorViewModel::class.java)
        val eq2Score = ViewModelProviders.of(this).get(contadorViewModel::class.java)

        val viewModelPartidoEquipo = ViewModelProviders.of(this).get(PartidoViewModel::class.java)

        viewModelPartidoEquipo.getThisPartidoId(hora,fecha).observe(this, Observer {id->
            partidoId=id
        })

        val scoreObserver1 = Observer<Int> { newInt ->
            tv_contTeam1.text = newInt.toString()
        }
        val scoreObserver2 = Observer<Int> { newInt ->
            tv_contTeam2.text = newInt.toString()
        }
        eq1Score.contEq1.observe(this, scoreObserver1)
        eq2Score.contEq2.observe(this, scoreObserver2)

        btn_eq1_1.setOnClickListener(){
            eq1Score.anotarA(1)
        }
        btn_eq1_2.setOnClickListener(){
            eq1Score.anotarA(2)
        }
        btn_eq1_3.setOnClickListener(){
            eq1Score.anotarA(3)
        }
        btn_eq2_1.setOnClickListener(){
            eq2Score.anotarB(1)
        }
        btn_eq2_2.setOnClickListener(){
            eq2Score.anotarB(2)
        }
        btn_eq2_3.setOnClickListener(){
            eq2Score.anotarB(3)
        }

        btn_endGame.setOnClickListener() {
            val flag= tv_contTeam1.text.toString().toLong()>= tv_contTeam2.text.toString().toLong()
            var eq1= Equipo(Eq1Name, tv_contTeam1.text.toString(), flag, partidoId)
            var eq2= Equipo(Eq2Name, tv_contTeam2.text.toString(), !flag, partidoId)
            viewModelPartidoEquipo.insertEquipo(eq1)
            viewModelPartidoEquipo.insertEquipo(eq2)
            startActivity(Intent(this@matchActivity,MainActivity::class.java))

        }

    }

    class contadorViewModel : ViewModel() {
        var aux1=0
        var aux2=0

        val contEq1 = MutableLiveData<Int>()
        val contEq2 = MutableLiveData<Int>()

        fun anotarA(i: Int) {
            aux1= aux1+i
            contEq1.postValue(aux1)
        }
        fun anotarB(i: Int) {
            aux2= aux2+i
            contEq2.postValue(aux2)
        }
    }
}