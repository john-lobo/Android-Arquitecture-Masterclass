package com.techyourchance.mvc.screens.common

interface BaseObservableViewListener<LISTENER_TYPE> : BaseViewListener {
    fun registerListener(listener: LISTENER_TYPE)
    fun unregisterListener(listener: LISTENER_TYPE)
}