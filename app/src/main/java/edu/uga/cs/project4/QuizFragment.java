package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is used to display the Quiz in the 'New Quiz' tab.
 */
public class QuizFragment extends Fragment {


    private ViewPager viewPager;
    static List<Question> questionList;
    ProjectDBHelper projectDBHelper;
    viewPageAdapter adapter;
    ConstraintLayout resultLayout;
    TextView txtTotalQuestion,txtAnswered,txtNotAnswered,txtScore;
    boolean isNewQuiz=true;
    boolean isSavedInDB=false;
    boolean isQuizFinished=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState!=null)
        {
            isNewQuiz=savedInstanceState.getBoolean("isNewQuiz");
            isQuizFinished=savedInstanceState.getBoolean("isQuizFinished");
            isSavedInDB=savedInstanceState.getBoolean("isSavedInDB");

        }

        resultLayout=view.findViewById(R.id.resultLayout);
        viewPager=view.findViewById(R.id.viewPager);
        txtTotalQuestion=view.findViewById(R.id.txtTotalQuestion);
        txtAnswered=view.findViewById(R.id.txtAnswered);
        txtNotAnswered=view.findViewById(R.id.txtNotAnswered);
        txtScore=view.findViewById(R.id.txtScore);

        projectDBHelper=ProjectDBHelper.getInstance(requireContext());

        if(isNewQuiz)
        {
            getSixRandomQuestions();
        }
        else
        {
            adapter=new viewPageAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
        }

        if(isQuizFinished)
        {
            showResultLayout();
        }
        else
        {
            showQuestionLayout();
        }


        isNewQuiz=false;


    }

    private void showQuestionLayout() {
        viewPager.setVisibility(View.VISIBLE);
        resultLayout.setVisibility(View.GONE);
    }

    private void showResultLayout() {
        viewPager.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);

        int answered=getAnswered();
        int score=getScore();
        int totalQuestions=questionList.size();
        txtTotalQuestion.setText("Total Question : "+totalQuestions);
        txtAnswered.setText("Answered : "+answered);
        txtNotAnswered.setText("Not Answered : "+(totalQuestions-answered));
        txtScore.setText("Score : "+score);

        if(!isSavedInDB)
        {

            String date=new SimpleDateFormat("dd/MM/yyy hh:mm:ss a", Locale.ENGLISH).format(new Date());
            Quiz quiz=new Quiz();
            quiz.setDate(date);
            quiz.setQ1(String.valueOf(questionList.get(0).getId()));
            quiz.setQ2(String.valueOf(questionList.get(1).getId()));
            quiz.setQ3(String.valueOf(questionList.get(2).getId()));
            quiz.setQ4(String.valueOf(questionList.get(3).getId()));
            quiz.setQ5(String.valueOf(questionList.get(4).getId()));
            quiz.setQ6(String.valueOf(questionList.get(5).getId()));
            quiz.setQuestionAnswered(String.valueOf(answered));
            quiz.setScore(String.valueOf(score));
            saveInDb(quiz);
            isSavedInDB=true;
        }
    }

    private void saveInDb(Quiz quiz) {

        new AsyncTask<Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... arguments) {


                return  projectDBHelper.insertQuizResult(quiz);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {

                if(aBoolean)
                {
                    Toast.makeText(requireContext(),"Result Saved",Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();
    }

    //gets the score of the quiz
    private int getScore() {
        int score=0;
        for(Question question:questionList)
            if(question.isAnswerCorrect)
                score++;

        return score;
    }

    //gets number answered in the quiz
    private int getAnswered() {
        int answered=0;
        for(Question question:questionList)
            if(question.isAnswered)
                answered++;

        return answered;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("isNewQuiz",isNewQuiz);
        outState.putBoolean("isSavedInDB",isSavedInDB);
        outState.putBoolean("isQuizFinished",isQuizFinished);
        super.onSaveInstanceState(outState);

    }

    //gets the random questions for the quiz
    private void getSixRandomQuestions() {
        AsyncTask<Void,List<Question>> task=new AsyncTask<Void,List<Question>>() {


            @Override
            protected List<Question> doInBackground(Void... arguments) {

                return   projectDBHelper.getQuestions();
            }

            @Override
            protected void onPostExecute(List<Question> questions) {

                questionList=questions;
                adapter=new viewPageAdapter(getChildFragmentManager());
                viewPager.setAdapter(adapter);
            }
        };

        task.execute();
    }


    public void onAnswer() {
        if(viewPager.getCurrentItem()==questionList.size()-1)
        {
            showResultLayout();
        }
    }



    private class viewPageAdapter extends FragmentStatePagerAdapter {
        public viewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            QuizQuestionFragment quizQuestionFragment=QuizQuestionFragment.newInstance(questionList.get(position));

            return quizQuestionFragment;
        }

        @Override
        public int getCount() {
            return questionList.size();
        }
    }
}