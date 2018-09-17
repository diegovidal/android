package br.com.vp.advancedandroid.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.Injector
import br.com.vp.advancedandroid.di.ScreenInjector
import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import br.com.vp.advancedandroid.ui.ActivityViewInterceptor
import br.com.vp.advancedandroid.ui.RouterProvider
import br.com.vp.advancedandroid.ui.ScreenNavigator
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import java.util.*
import javax.inject.Inject

/**
 * @author diegovidal on 11/04/2018.
 */

abstract class BaseActivity: AppCompatActivity(), RouterProvider{

    var instanceId = ""

    @Inject lateinit var screenInjector: ScreenInjector
    @Inject lateinit var screenNavigator: ScreenNavigator
    @Inject lateinit var activityViewInterceptor: ActivityViewInterceptor
    @Inject lateinit var activityLifecycleTask: MutableSet<ActivityLifecycleTask>

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {

        instanceId = if (savedInstanceState != null) {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } else {
            UUID.randomUUID().toString()
        }

        Injector.inject(this)
        activityViewInterceptor.setContentView(this, layoutRes())

        val screenContainer = findViewById<ViewGroup>(R.id.screen_container) ?:
            throw NullPointerException("Activity must have a view with id: screen_container")

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        monitorBackStack()

        for (task in activityLifecycleTask){
            task.onCreate(this)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        for (task in activityLifecycleTask){
            task.onStart(this)
        }
    }

    override fun onResume() {
        super.onResume()
        for (task in activityLifecycleTask){
            task.onResume(this)
        }
    }

    override fun onPause() {
        super.onPause()
        for (task in activityLifecycleTask){
            task.onPause(this)
        }
    }

    override fun onStop() {
        super.onStop()
        for (task in activityLifecycleTask){
            task.onStop(this)
        }
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

        if (isFinishing){
            Injector.clearComponent(this)
        }
        activityViewInterceptor.clear()
        for (task in activityLifecycleTask){
            task.onDestroy(this)
        }
    }

    override fun getRouter(): Router {

        return router
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    abstract override fun initialScreen(): Controller

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