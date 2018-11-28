package com.dvidal.torneioscontinentais.activity

import android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.app_bar_main.*
import com.dvidal.torneioscontinentais.R
import com.dvidal.torneioscontinentais.fragment.AbstractFragment
import com.dvidal.torneioscontinentais.fragment.AfcFragment
import com.dvidal.torneioscontinentais.fragment.LibertadoresFragment
import com.dvidal.torneioscontinentais.fragment.UefaFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_nav_view.setOnNavigationItemSelectedListener(this)
        bottom_nav_view.setOnNavigationItemReselectedListener(this)

        replaceFragment(LibertadoresFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment = when (item.itemId) {

            R.id.nav_libertadores -> {
                title = "Taça Libertadores"
                LibertadoresFragment()
            }

            R.id.nav_afc -> {
                title = "Liga dos Campeões da AFC"
                AfcFragment()
            }

            R.id.nav_uefa -> {
                title = "UEFA Champions League"
                UefaFragment()
            }

            else -> throw IllegalArgumentException()
        }

        replaceFragment(fragment)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.rl_container, fragment, "AbstractFragmentKey")
                .commit()
    }

    override fun onNavigationItemReselected(p0: MenuItem) {
        Log.i("LOG", "onNavigationItemReselected()")
    }

    fun toolbarIsVisible(status: Boolean) {

        toolbar.visibility = if (status) View.VISIBLE else View.GONE
    }
}
