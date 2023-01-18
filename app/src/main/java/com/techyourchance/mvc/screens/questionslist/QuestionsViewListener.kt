package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewListener

interface QuestionsViewListener : BaseObservableViewListener<QuestionsViewListener.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question?)
    }
    fun bindQuestions(questions: List<Question>)
}