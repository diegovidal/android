package br.com.vp.advancedandroid.home

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.trending.TrendingReposController
import com.bluelinelabs.conductor.Controller

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int {

        return R.layout.activity_main
    }

    override fun initialScreen(): Controller {

        return TrendingReposController()
    }
}
