package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private View rootView;
    private ListView mLstQuestions;
    private QuestionsListAdapter mQuestionsListAdapter;

    private final List<Listener> mListeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater , ViewGroup parent){
            rootView = inflater.inflate(R.layout.layout_questions_list, parent,false);

        mLstQuestions = findViewById(R.id.lst_questions);
        mQuestionsListAdapter = new QuestionsListAdapter(getContext(), this);
        mLstQuestions.setAdapter(mQuestionsListAdapter);

    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return rootView;
    }


    @Override
    public void registerListener(Listener listener){
        mListeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener){
        mListeners.remove(listener);
    }

    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : mListeners){
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListAdapter.clear();
        mQuestionsListAdapter.addAll(questions);
        mQuestionsListAdapter.notifyDataSetChanged();


    }

}
