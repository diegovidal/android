package com.dvidal.torneioscontinentais.fragment

import kotlinx.android.synthetic.main.lista_jogos.*
import com.dvidal.torneioscontinentais.adapter.GamesAdapter
import com.dvidal.torneioscontinentais.data.MockData

class AfcFragment : AbstractFragment() {

    override fun onStart() {
        super.onStart()
        rv_jogos.adapter = GamesAdapter(requireContext(), MockData.getAfcTimes())
    }
}