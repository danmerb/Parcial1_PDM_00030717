package com.example.parcial1basketball.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.parcial1basketball.R
import com.example.parcial1basketball.room.entities.Partido
import com.example.parcial1basketball.viewModels.PartidoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_match.*

class addMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)
        val viewModel = ViewModelProviders.of(this).get(PartidoViewModel::class.java)
        btn_enviarpartido.setOnClickListener { view ->

            if (et_eq1.text.isNotBlank() &&  et_eq2.text.isNotBlank() && et_fecha.text.isNotBlank()

                && et_hora.text.isNotBlank())
            {

                var partido= Partido(et_hora.text.toString(), et_fecha.text.toString())
                var partidoBundle = Bundle()
                partidoBundle.putParcelable("partido", partido)
                viewModel.insertMatch(partido)



                var intent = Intent(this, matchActivity::class.java)
                intent.putExtra("team_eq1", et_eq1.text.toString())
                intent.putExtra("team_eq2", et_eq2.text.toString())
                intent.putExtra("Match", partidoBundle)
                startActivity(intent)
            }
            else   {
                Snackbar.make(view, "Necesario completar todos los campos", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
