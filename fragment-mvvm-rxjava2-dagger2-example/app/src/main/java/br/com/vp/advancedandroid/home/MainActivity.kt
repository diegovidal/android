package br.com.vp.advancedandroid.home

import android.support.v4.app.Fragment
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.trending.TrendingReposFragment

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int {

        return R.layout.activity_main
    }

    override fun initialScreen(): Fragment {

        return TrendingReposFragment()
    }
}
