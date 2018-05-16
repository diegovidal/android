package br.com.vp.advancedandroid.lifecycle

import android.view.View

/**
 * @author diegovidal on 10/05/2018.
 */
open class ScreenLifecycleTask {

    /**
     * Callback received when a Screen becomes the visible screen.
     */
    open fun onEnterScope(view: View?){

    }

    /**
     * Callback received when a Screen is either popped of moved to the back stack.
     */
    open fun onExitScope() {

    }

    /**
     * Callback received when a Screen is destroyed and will not be coming back (except as a new instance). This should
     * be used to clear any {@link ActivityScope} connections (Disposables, etc).
     */
    open fun onDestroy(){

    }
}