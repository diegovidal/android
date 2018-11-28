package com.dvidal.torneioscontinentais.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dvidal.torneioscontinentais.R
import com.dvidal.torneioscontinentais.activity.MainActivity
import com.dvidal.torneioscontinentais.domain.Team
import kotlinx.android.synthetic.main.activity_team_details.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamDetailsFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        (activity as? MainActivity)?.toolbarIsVisible(false)
        return inflater.inflate(R.layout.activity_team_details, container, false)
    }

    override fun onResume() {
        super.onResume()

        toolbar.setNavigationOnClickListener(this)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

        arguments?.getParcelable<Team>(Team.KEY)?.let { team ->

            toolbar.title = team.nome
            iv_torcida_time.setImageResource(team.idEscudo)
        }
    }

    override fun onClick(v: View?) {

        activity?.onBackPressed()
    }

    override fun onDetach() {

        (activity as? MainActivity)?.toolbarIsVisible(true)
        super.onDetach()
    }
}
