package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import com.techyourchance.mvc.screens.questionslist.adapter.QuestionsListAdapter
import com.techyourchance.mvc.screens.questionslist.adapter.QuestionsRecyclerAdapter

class QuestionsView(private val inflater: LayoutInflater, parent: ViewGroup?) :
    QuestionsListAdapter.OnQuestionClickListener, QuestionsRecyclerAdapter.OnQuestionClickListener, QuestionsViewListener {
    override val rootView: View

    private val questionsRecyclerView: RecyclerView
    private lateinit var questionAdapter: QuestionsRecyclerAdapter
    private val listeners: MutableList<QuestionsViewListener.Listener?> = ArrayList(1)

    init {
        rootView = inflater.inflate(R.layout.layout_questions_list, parent, false)
        questionsRecyclerView = findViewById(R.id.questionsRecyclerView)
    }

    private val context: Context
        private get() = rootView.context

    private fun <T : View?> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    override fun registerListener(listener: QuestionsViewListener.Listener?) {
        listeners.add(listener)
    }

    override fun removeListener(listener: QuestionsViewListener.Listener?) {
        listeners.remove(listener)
    }

    override fun onQuestionClicked(question: Question?) {
        for (listener in listeners) {
            listener!!.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        questionAdapter = QuestionsRecyclerAdapter(questions , inflater, this)
        questionsRecyclerView.adapter = questionAdapter
    }
}