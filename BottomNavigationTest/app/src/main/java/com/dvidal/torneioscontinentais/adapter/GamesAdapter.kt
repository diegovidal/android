package com.dvidal.torneioscontinentais.adapter

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.dvidal.torneioscontinentais.R
import com.dvidal.torneioscontinentais.activity.MainActivity
import com.dvidal.torneioscontinentais.domain.Game
import com.dvidal.torneioscontinentais.domain.Team
import com.dvidal.torneioscontinentais.fragment.TeamDetailsFragment


class GamesAdapter(
        private val context: Context,
        private val pokemons: List<Game>) :
        RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int) : GamesAdapter.ViewHolder {

        val v = LayoutInflater
                .from(context)
                .inflate(R.layout.jogo, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        var llTc: LinearLayout
        var ivTcBandeiraPais: ImageView
        var ivTcEscudo: ImageView
        var tvTcNome: TextView
        var llTv: LinearLayout
        var ivTvBandeiraPais: ImageView
        var ivTvEscudo: ImageView
        var tvTvNome: TextView
        var tvData: TextView
        var tvHorario: TextView
        var tvEstadio: TextView

        init {
            llTc = itemView.findViewById(R.id.ll_tc)
            llTc.setOnClickListener(this)
            ivTcBandeiraPais = itemView.findViewById(R.id.iv_tc_bandeira_pais)
            ivTcEscudo = itemView.findViewById(R.id.iv_tc_escudo)
            tvTcNome = itemView.findViewById(R.id.tv_tc_nome)

            llTv = itemView.findViewById(R.id.ll_tv)
            llTv.setOnClickListener(this)
            ivTvBandeiraPais = itemView.findViewById(R.id.iv_tv_bandeira_pais)
            ivTvEscudo = itemView.findViewById(R.id.iv_tv_escudo)
            tvTvNome = itemView.findViewById(R.id.tv_tv_nome)

            tvData = itemView.findViewById(R.id.tv_data)
            tvHorario = itemView.findViewById(R.id.tv_horario)
            tvEstadio = itemView.findViewById(R.id.tv_estadio)
        }

        fun setData(game: Game) {
            ivTcBandeiraPais.setImageResource( game.teamCasa.idBandeiraPais )
            ivTcEscudo.setImageResource( game.teamCasa.idEscudo )
            tvTcNome.text = game.teamCasa.nome
            llTc.tag = game.teamCasa
            estrelasCampeonatos(llTc, game.teamCasa.qtdCampeonatos)

            ivTvBandeiraPais.setImageResource( game.teamVisitante.idBandeiraPais )
            ivTvEscudo.setImageResource( game.teamVisitante.idEscudo )
            tvTvNome.text = game.teamVisitante.nome
            llTv.tag = game.teamVisitante
            estrelasCampeonatos(llTv, game.teamVisitante.qtdCampeonatos)

            tvData.text = game.data
            tvHorario.text = game.horario
            tvEstadio.text = game.estadio
        }

        private fun estrelasCampeonatos(ll: LinearLayout, qtdCampeonatos: Int){

            for ( i in 0..ll.childCount ) {

                if( ll.getChildAt(i) is FlexboxLayout ){
                    val view = ll.getChildAt(i) as FlexboxLayout

                    for ( j in 0..(qtdCampeonatos - 1) ) {
                        view.getChildAt(j).visibility = View.VISIBLE
                    }
                    for ( j in qtdCampeonatos..(view.childCount - 1) ) {
                        view.getChildAt(j).visibility = View.GONE
                    }
                }
            }
        }

        override fun onClick(view: View?) {

            val bundle = Bundle()
            bundle.putParcelable(Team.KEY, view?.tag as Team)

            val fragment = TeamDetailsFragment()
            fragment.arguments = bundle

            (context as? MainActivity)
                    ?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.rl_container, fragment, "AbstractFragmentKey")
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }
}