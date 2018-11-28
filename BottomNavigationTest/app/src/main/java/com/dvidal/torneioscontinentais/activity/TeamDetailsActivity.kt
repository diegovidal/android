package com.dvidal.torneioscontinentais.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_team_details.*
import com.dvidal.torneioscontinentais.R
import com.dvidal.torneioscontinentais.domain.Team

class TeamDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
        setSupportActionBar(toolbar)

        /*
         * Para liberar o back button na barra de topo da
         * atividade.
         * */
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    /*
     * Hack code para que a atualização de título de atividade
     * seja realizada.
     * */
    override fun onResume() {
        super.onResume()

        val time = intent.getParcelableExtra<Team>(Team.KEY)

        toolbar.title = time!!.nome
    }

    /*
     * Para permitir que o back button tenha a ação de volta a
     * atividade anterior.
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
