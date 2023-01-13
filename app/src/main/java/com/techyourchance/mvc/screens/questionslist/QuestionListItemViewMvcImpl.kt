package com.techyourchance.mvc.screens.questionslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question

class QuestionListItemViewMvcImpl(layoutInflater: LayoutInflater, parent: ViewGroup?) :
    QuestionListItemViewMvc {
    override val rootView: View
    private val mListener: MutableList<QuestionListItemViewMvc.Listener?> = ArrayList(1)
    private var mQuestion: Question? = null
    private val mTxtTitle: TextView
    private val mRecyclerQuestions: RecyclerView? = null
    private val mAdapter: QuestionsListAdapter? = null

    init {
        rootView = layoutInflater.inflate(R.layout.layout_question_list_item, parent, false)
        mTxtTitle = findViewById(R.id.txt_title)
        rootView.setOnClickListener {
            for (listener in mListener) {
                listener!!.onQuestionClicked(mQuestion)
            }
        }
    }

    private fun <T : View?> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    override fun registerListener(listener: QuestionListItemViewMvc.Listener?) {
        mListener.add(listener)
    }

    override fun unregisterListener(listener: QuestionListItemViewMvc.Listener?) {
        mListener.remove(listener)
    }

    override fun bindQuestion(question: Question?) {
        mQuestion = question
        mTxtTitle.text = question!!.title
    }
}