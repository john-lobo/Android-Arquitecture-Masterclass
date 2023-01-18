package com.techyourchance.mvc.screens.questionslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.common.BaseObservableView
import com.techyourchance.mvc.screens.questionslist.adapter.QuestionsListAdapter
import com.techyourchance.mvc.screens.questionslist.adapter.QuestionsRecyclerAdapter

class QuestionsView(private val inflater: LayoutInflater, parent: ViewGroup?) : BaseObservableView<QuestionsViewListener.Listener>(inflater.inflate(R.layout.layout_questions_list, parent, false)),
    QuestionsListAdapter.OnQuestionClickListener, QuestionsRecyclerAdapter.OnQuestionAdapterLister, QuestionsViewListener {

    private var questionsRecyclerView: RecyclerView? = null
    private lateinit var questionAdapter: QuestionsRecyclerAdapter

    init {
        questionsRecyclerView = findViewById(R.id.questionsRecyclerView)
    }

    override fun onQuestionClicked(question: Question?) {
        for (listener in listeners) {
            listener.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        questionAdapter = QuestionsRecyclerAdapter(questions , inflater, this)
        questionsRecyclerView?.adapter = questionAdapter
    }
}