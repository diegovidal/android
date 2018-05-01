package br.com.vp.advancedandroid.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.vp.advancedandroid.di.Injector
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author diegovidal on 18/04/2018.
 */
 abstract class BaseController: Controller() {

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

    override fun onDestroyView(view: View) {

        disposables.clear()
        unbinder?.unbind()
        unbinder = null
    }

    internal open fun onViewBound(view: View) {

    }

    internal open fun subscriptions(): Array<Disposable> {

        return arrayOf()
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int
}