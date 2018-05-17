package br.com.vp.advancedandroid.trending

import android.support.v7.widget.Toolbar
import android.view.View
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import br.com.vp.advancedandroid.util.ButterKnifeUtils
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import javax.inject.Inject

/**
 * @author diegovidal on 10/05/2018.
 */

@ScreenScope
class TrendingReposUiManager @Inject
        constructor()
    : ScreenLifecycleTask() {

    @BindView(R.id.my_toolbar) lateinit var toolbar: Toolbar

    private var unbinder: Unbinder? = null

    override fun onEnterScope(view: View?) {

        view?.let {
            unbinder = ButterKnife.bind(this, view)
            toolbar.setTitle(R.string.screen_title_trending)
        }

    }

    override fun onExitScope() {
        ButterKnifeUtils.unbind(unbinder)
    }
}