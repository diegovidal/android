package br.com.vp.advancedandroid.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.vp.advancedandroid.di.Injector
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import butterknife.ButterKnife
import butterknife.Unbinder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author diegovidal on 18/04/2018.
 */
 abstract class BaseFragment: Fragment() {

    @Inject lateinit var screenLifecycleTask: MutableSet<ScreenLifecycleTask>

    private val disposables = CompositeDisposable()
    private var unbinder: Unbinder? = null

    override fun onAttach(context: Context?) {
        Injector.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes(), container, false)

        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(*subscriptions())

        for (task in screenLifecycleTask){
            task.onEnterScope(view)
        }

        return view
    }

    override fun onDestroyView() {
        disposables.clear()
        unbinder?.unbind()
        unbinder = null
        for (task in screenLifecycleTask){
            task.onExitScope()
        }
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        for (task in screenLifecycleTask){
            task.onDestroy()
        }

        if (activity?.isChangingConfigurations == true) {
            Injector.clearComponent(this)
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