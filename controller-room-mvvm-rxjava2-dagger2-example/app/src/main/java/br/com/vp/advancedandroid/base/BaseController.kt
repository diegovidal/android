package br.com.vp.advancedandroid.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.vp.advancedandroid.di.Injector
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author diegovidal on 18/04/2018.
 */
 abstract class BaseController(bundle: Bundle? = null): Controller(bundle) {

    @Inject lateinit var screenLifecycleTask: MutableSet<ScreenLifecycleTask>

    private val disposables = CompositeDisposable()
    private var injected = false
    private var unbinder: Unbinder? = null

    override fun onContextAvailable(context: Context) {

        // Controller instances are retained across config changes, so this method can be called more than once. This makes
        // sure we don't wast any time injecting more than once, though technically it would't change functionality
        if (!injected){
            Injector.inject(this)
            injected = true
        }

        super.onContextAvailable(context)
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes(), container, false)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(*subscriptions())
        return view
    }

    override fun onChangeStarted(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {

        for (task in screenLifecycleTask){
            if (changeType.isEnter){
                task.onEnterScope(view)
            } else {
                task.onExitScope()
            }
        }
    }

    override fun onDestroyView(view: View) {

        disposables.clear()
        unbinder?.unbind()
        unbinder = null
    }

    override fun onDestroy() {
        super.onDestroy()
        for (task in screenLifecycleTask){
            task.onDestroy()
        }
    }

    internal open fun onViewBound(view: View) {

    }

    internal open fun subscriptions(): Array<Disposable> {

        return arrayOf()
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int
}