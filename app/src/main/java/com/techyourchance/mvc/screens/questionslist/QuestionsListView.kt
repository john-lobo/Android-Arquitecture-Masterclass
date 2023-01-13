package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.techyourchance.mvc.screens.questionslist.QuestionsListAdapter.OnQuestionClickListener
import com.techyourchance.mvc.R
import com.techyourchance.mvc.questions.Question
import java.util.ArrayList

class QuestionsListView(inflater: LayoutInflater, parent: ViewGroup?) :
    OnQuestionClickListener, QuestionsListViewContract {
    override val rootView: View

    private val mLstQuestions: ListView
    private val mQuestionsListAdapter: QuestionsListAdapter
    private val mListeners: MutableList<QuestionsListViewContract.Listener?> = ArrayList(1)

    init {
        rootView = inflater.inflate(R.layout.layout_questions_list, parent, false)
        mLstQuestions = findViewById(R.id.lst_questions)
        mQuestionsListAdapter = QuestionsListAdapter(context, this)
        mLstQuestions.adapter = mQuestionsListAdapter
    }

    private val context: Context
        private get() = rootView.context

    private fun <T : View?> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    override fun registerListener(listener: QuestionsListViewContract.Listener?) {
        mListeners.add(listener)
    }

    override fun removeListener(listener: QuestionsListViewContract.Listener?) {
        mListeners.remove(listener)
    }

    override fun onQuestionClicked(question: Question?) {
        for (listener in mListeners) {
            listener!!.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(questions: List<Question?>?) {
        mQuestionsListAdapter.clear()
        mQuestionsListAdapter.addAll(questions!!)
        mQuestionsListAdapter.notifyDataSetChanged()
    }
}