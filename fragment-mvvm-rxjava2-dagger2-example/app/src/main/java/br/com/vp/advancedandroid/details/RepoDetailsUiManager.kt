package br.com.vp.advancedandroid.details

import android.support.v7.widget.Toolbar
import android.view.View
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import br.com.vp.advancedandroid.ui.ScreenNavigator
import br.com.vp.advancedandroid.util.ButterKnifeUtils
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import javax.inject.Inject
import javax.inject.Named

/**
 * @author diegovidal on 10/05/2018.
 */

@ScreenScope
class RepoDetailsUiManager @Inject
        constructor(@Named("repo_name") val repoName: String,
                    val screenNavigator: ScreenNavigator)
    : ScreenLifecycleTask() {

    @BindView(R.id.my_toolbar) lateinit var toolbar: Toolbar

    private var unbinder: Unbinder? = null

    override fun onEnterScope(view: View?) {

        view?.let {
            unbinder = ButterKnife.bind(this, view)
            toolbar.title = repoName
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener { v ->
                screenNavigator.pop()
            }
        }
    }

    override fun onExitScope() {

        toolbar.setNavigationOnClickListener(null)
        ButterKnifeUtils.unbind(unbinder)
    }
}