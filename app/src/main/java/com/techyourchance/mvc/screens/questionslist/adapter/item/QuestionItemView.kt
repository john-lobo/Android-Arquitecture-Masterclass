package com.techyourchance.mvc.screens.questionslist.adapter.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableView
import com.techyourchance.mvc.screens.common.BaseView

class QuestionItemView(layoutInflater: LayoutInflater, parent: ViewGroup?) : BaseObservableView<QuestionItemViewListener.Listener>(layoutInflater.inflate(R.layout.layout_question_list_item, parent, false)),
    QuestionItemViewListener {

    private var question: Question? = null
    private var titleView: TextView? = null

    init {
        titleView = findViewById(R.id.txt_title)
        rootView.setOnClickListener {
            listeners.forEach { it.onQuestionClicked(question) }
        }
    }

    override fun bindQuestion(question: Question?) {
        this.question = question
        titleView?.text = question!!.title
    }
}