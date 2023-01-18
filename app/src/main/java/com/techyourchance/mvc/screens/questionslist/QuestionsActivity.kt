package com.techyourchance.mvc.screens.questionslist

import com.techyourchance.mvc.screens.common.BaseActivity
import com.techyourchance.mvc.networking.StackoverflowApi
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.techyourchance.mvc.networking.QuestionsListResponseSchema
import com.techyourchance.mvc.networking.QuestionSchema
import com.techyourchance.mvc.questions.Question
import android.widget.Toast
import com.techyourchance.mvc.R
import com.techyourchance.mvc.common.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class QuestionsActivity : BaseActivity(), QuestionsViewListener.Listener {

    private var mStackoverflowApi: StackoverflowApi? = null
    private var mViewMvc: QuestionsViewListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewMvc = QuestionsView(LayoutInflater.from(this), null).apply {
            registerListener(this@QuestionsActivity)
        }

        mStackoverflowApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StackoverflowApi::class.java)

        setContentView(mViewMvc!!.rootView)
    }

    override fun onStart() {
        super.onStart()
        fetchQuestions()
    }

    private fun fetchQuestions() {
        mStackoverflowApi!!.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)!!
            .enqueue(object : Callback<QuestionsListResponseSchema?> {
                override fun onResponse(
                    call: Call<QuestionsListResponseSchema?>?,
                    response: Response<QuestionsListResponseSchema?>?
                ) {
                    if (response!!.isSuccessful) {
                        bindQuestions(response.body()!!.questions)
                    } else {
                        networkCallFailed()
                    }
                }

                override fun onFailure(call: Call<QuestionsListResponseSchema?>?, t: Throwable) {
                    Log.e("errorApi", "onFailure: " + t.message)
                    networkCallFailed()
                }
            })
    }

    private fun bindQuestions(questionSchemas: List<QuestionSchema>) {
        val questions: MutableList<Question> = ArrayList(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.id, questionSchema.title))
        }
        mViewMvc!!.bindQuestions(questions)
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionClicked(question: Question?) {
        Toast.makeText(this, question?.title, Toast.LENGTH_SHORT).show()
    }
}