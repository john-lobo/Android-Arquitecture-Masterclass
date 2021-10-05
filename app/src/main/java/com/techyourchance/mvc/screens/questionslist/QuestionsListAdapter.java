package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question> implements QuestionListItemViewMvc.Listener {

    private final OnQuestionClickListener mOnQuestionClickListener;

    public interface
    OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    public QuestionsListAdapter(Context context,
                                OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        mOnQuestionClickListener = onQuestionClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            QuestionListItemViewMvc viewMvc = new QuestionListItemViewMvcImpl(
                    LayoutInflater.from(getContext()),parent
            );
            viewMvc.registerListener(this);
            convertView = viewMvc.getRootView();
            convertView.setTag(viewMvc);
        }

        final Question question = getItem(position);

        // bind the data to views
        QuestionListItemViewMvc viewMvc = (QuestionListItemViewMvc) convertView.getTag();
        viewMvc.bindQuestion(question);

        // set listener
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onQuestionClicked(question);
            }
        });

        return convertView;
    }

    @Override
    public void onQuestionClicked(Question question) {
        mOnQuestionClickListener.onQuestionClicked(question);
    }

}
