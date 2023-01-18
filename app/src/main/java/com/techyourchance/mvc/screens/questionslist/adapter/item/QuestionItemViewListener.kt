package com.techyourchance.mvc.screens.questionslist.adapter.item

import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableViewListener

interface QuestionItemViewListener : BaseObservableViewListener<QuestionItemViewListener.Listener>{

    fun bindQuestion(question: Question?)

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }
}
