package com.techyourchance.mvc.screens.questionslist.adapter

import com.techyourchance.mvc.screens.questionslist.adapter.item.QuestionItemView
import com.techyourchance.mvc.screens.questionslist.adapter.item.QuestionItemViewListener

import android.support.v7.widget.RecyclerView
import com.techyourchance.mvc.questions.Question
import android.view.ViewGroup
import android.view.LayoutInflater

class QuestionsRecyclerAdapter(
    private val questions: List<Question>,
    private val inflater: LayoutInflater,
    private val listener: OnQuestionAdapterLister
) : RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(), QuestionItemViewListener.Listener {

    interface OnQuestionAdapterLister {
        fun onQuestionClicked(question: Question?)
    }

    inner class MyViewHolder(val view : QuestionItemView) : RecyclerView.ViewHolder(view.rootView)

    override fun onQuestionClicked(question: Question?) {
        listener.onQuestionClicked(question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val questionItem = QuestionItemView(inflater, parent)
        questionItem.registerListener(this)
        return MyViewHolder(questionItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.bindQuestion(questions[position])
    }

    override fun getItemCount() = questions.size
}