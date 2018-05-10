package br.com.vp.advancedandroid.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.settings.DebugPreferences
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author diegovidal on 09/05/2018.
 */
class DebugActivityViewInterceptor @Inject
        constructor(val debugPreferences: DebugPreferences)
    : ActivityViewInterceptor {

    @BindView(R.id.sw_mock_responses) lateinit var mockResponseSwitch: Switch

    private val disposable = CompositeDisposable()
    private var unbinder: Unbinder? = null

    override fun setContentView(activity: Activity, layoutRes: Int) {

        val debugLayout = LayoutInflater.from(activity).inflate(R.layout.debug_drawer, null)
        unbinder = ButterKnife.bind(this, debugLayout)
        initializePrefs()

        val activityLayout = LayoutInflater.from(activity).inflate(layoutRes, null)
        debugLayout.findViewById<ViewGroup>(R.id.activity_layout_container).addView(activityLayout)
        activity.setContentView(debugLayout)
    }

    override fun clear() {

        disposable.clear()
        unbinder?.unbind()
        unbinder = null
    }

    private fun initializePrefs() {

        mockResponseSwitch.isChecked = debugPreferences.useMockResponsesEnabled()

        disposable.addAll(

                RxCompoundButton.checkedChanges(mockResponseSwitch)
                        .subscribe(debugPreferences::setUseMockResponses)
        )
    }
}