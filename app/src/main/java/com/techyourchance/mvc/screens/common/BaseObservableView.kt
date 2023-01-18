package com.techyourchance.mvc.screens.common

import android.view.View
import java.util.Collections

open class BaseObservableView<LISTENER_TYPE>(view : View): BaseView(view) , BaseObservableViewListener<LISTENER_TYPE> {


    val listeners = HashSet<LISTENER_TYPE>()

    override fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }

//    protected fun getListeners(): Set<LISTENER_TYPE> {
//        return Collections.unmodifiableSet(listeners)
//    }

}

