package com.techyourchance.mvc.screens.questionslist

import android.view.View
import com.techyourchance.mvc.questions.Question

interface QuestionsListViewContract {
    interface Listener {
        fun onQuestionClicked(question: Question?)
    }

    val rootView: View
    fun registerListener(listener: Listener?)
    fun removeListener(listener: Listener?)
    fun bindQuestions(questions: List<Question?>?)
}