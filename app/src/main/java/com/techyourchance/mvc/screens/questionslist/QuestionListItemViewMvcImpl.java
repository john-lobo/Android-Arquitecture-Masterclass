package com.techyourchance.mvc.screens.questionslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionListItemViewMvcImpl implements QuestionListItemViewMvc {

    private View mRootView;
    private List<Listener> mListener = new ArrayList<>(1);
    private Question mQuestion;
    private TextView mTxtTitle;

    private RecyclerView mRecyclerQuestions;
    private QuestionsListAdapter mAdapter;

    public QuestionListItemViewMvcImpl(LayoutInflater layoutInflater, ViewGroup parent){

        mRootView = layoutInflater.inflate(R.layout.layout_question_list_item,parent,false);

        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Listener listener : mListener){
                    listener.onQuestionClicked(mQuestion);
                }
            }
        });
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void registerListener(Listener listener) {
        mListener.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListener.remove(listener);
    }

    @Override
    public void bindQuestion(Question question) {
        mQuestion = question;
        mTxtTitle.setText(question.getTitle());
    }
}
