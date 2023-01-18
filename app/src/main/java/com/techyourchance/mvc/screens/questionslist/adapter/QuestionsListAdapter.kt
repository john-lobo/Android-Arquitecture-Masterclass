package com.techyourchance.mvc.screens.questionslist.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.techyourchance.mvc.questions.Question
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.techyourchance.mvc.screens.questionslist.adapter.item.QuestionItemView
import com.techyourchance.mvc.screens.questionslist.adapter.item.QuestionItemViewListener

class QuestionsListAdapter(
    context: Context?,
    private val mOnQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question?>(context!!, 0), QuestionItemViewListener.Listener {
    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question?)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val viewMvc: QuestionItemViewListener = QuestionItemView(
                LayoutInflater.from(context), parent
            )
            viewMvc.registerListener(this)
            convertView = viewMvc.rootView
            convertView!!.tag = viewMvc
        }
        val question = getItem(position)

        // bind the data to views
        val viewMvc = convertView.tag as QuestionItemViewListener
        viewMvc.bindQuestion(question)

        // set listener
        convertView!!.setOnClickListener { onQuestionClicked(question) }
        return convertView
    }

    override fun onQuestionClicked(question: Question?) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }
}