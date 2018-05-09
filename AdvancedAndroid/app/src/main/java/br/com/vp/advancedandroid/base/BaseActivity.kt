package br.com.vp.advancedandroid.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.vp.advancedandroid.di.Injector
import br.com.vp.advancedandroid.di.ScreenInjector
import br.com.vp.advancedandroid.ui.ScreenNavigator
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

/**
 * @author diegovidal on 11/04/2018.
 */

abstract class BaseActivity: AppCompatActivity(){

    var instanceId = ""

    @Inject
    lateinit var screenInjector: ScreenInjector

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {

        instanceId = if (savedInstanceState != null) {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } else {
            UUID.randomUUID().toString()
        }

        Injector.inject(this)
        setContentView(layoutRes())

        if (screenContainer == null){
            throw NullPointerException("Activity must have a view with id: screen_container")
        }

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        screenNavigator.initWithRouter(router, initialScreen())
        monitorBackStack()
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onBackPressed() {

        if (!screenNavigator.pop()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenNavigator.clear()

        if (isFinishing){
            Injector.clearComponent(this)
        }
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun initialScreen(): Controller

    private fun monitorBackStack() {

        router.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener{

            override fun onChangeStarted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler
            ) {


            }

            override fun onChangeCompleted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler
            ) {

                if (!isPush && from != null){
                    Injector.clearComponent(from)
                }
            }
        })
    }

    companion object {

        private const val INSTANCE_ID_KEY = "instance_id"
    }
}