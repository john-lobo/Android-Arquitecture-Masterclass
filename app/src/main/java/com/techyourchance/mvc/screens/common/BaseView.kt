package com.techyourchance.mvc.screens.common

import android.content.Context
import android.view.View

abstract class BaseView(private val view: View) : BaseViewListener {
    override val rootView: View
        get() = view

    protected fun <T : View?> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    protected val context: Context
        get() = rootView.context
}