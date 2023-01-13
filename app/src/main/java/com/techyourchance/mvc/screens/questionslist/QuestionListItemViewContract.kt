package com.techyourchance.mvc.screens.questionslist

import android.view.View
import com.techyourchance.mvc.questions.Question

interface QuestionListItemViewContract {

    val rootView: View?
    fun registerListener(listener: Listener?)
    fun unregisterListener(listener: Listener?)
    fun bindQuestion(question: Question?)

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }
}
