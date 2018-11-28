package com.dvidal.torneioscontinentais.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lista_jogos.*
import com.dvidal.torneioscontinentais.R

abstract class AbstractFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.lista_jogos, container, false)
    }

    override fun onStart() {
        super.onStart()

        rv_jogos.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        rv_jogos.layoutManager = layoutManager
        val divider = DividerItemDecoration(activity, layoutManager.orientation)
        rv_jogos.addItemDecoration(divider)
    }
}