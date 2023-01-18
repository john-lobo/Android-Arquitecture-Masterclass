package com.techyourchance.mvc.screens.questionslist.adapter.item

import android.view.View
import com.techyourchance.mvc.questions.Question

interface QuestionItemViewListener {

    val rootView: View?
    fun registerListener(listener: Listener?)
    fun unregisterListener(listener: Listener?)
    fun bindQuestion(question: Question?)

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }
}
