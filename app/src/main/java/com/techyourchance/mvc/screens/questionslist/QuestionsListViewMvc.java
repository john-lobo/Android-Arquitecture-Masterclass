package com.techyourchance.mvc.screens.questionslist;

import android.view.View;

import com.techyourchance.mvc.questions.Question;

import java.util.List;

public interface QuestionsListViewMvc {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    View getRootView();

    void registerListener(Listener listener);

    void removeListener(Listener listener);

    void bindQuestions(List<Question> questions);


}
