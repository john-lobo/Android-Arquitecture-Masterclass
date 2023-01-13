package com.techyourchance.mvc.screens.questionslist

import android.content.Context
import android.widget.ArrayAdapter
import com.techyourchance.mvc.questions.Question
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

class QuestionsListAdapter(
    context: Context?,
    private val mOnQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question?>(context!!, 0), QuestionListItemViewContract.Listener {
    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question?)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val viewMvc: QuestionListItemViewContract = QuestionListItemView(
                LayoutInflater.from(context), parent
            )
            viewMvc.registerListener(this)
            convertView = viewMvc.rootView
            convertView!!.tag = viewMvc
        }
        val question = getItem(position)

        // bind the data to views
        val viewMvc = convertView.tag as QuestionListItemViewContract
        viewMvc.bindQuestion(question)

        // set listener
        convertView!!.setOnClickListener { onQuestionClicked(question) }
        return convertView
    }

    override fun onQuestionClicked(question: Question?) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }
}