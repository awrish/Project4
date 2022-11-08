package edu.uga.cs.project4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizQuestionFragment extends Fragment {



    private static final String ARG_PARAM1 = "param1";

    private  Question question;

    public void setAnswerListener(AnswerListener answerListener) {
        this.answerListener = answerListener;
    }

    AnswerListener answerListener;
    public QuizQuestionFragment() {
        // Required empty public constructor
    }



    public static QuizQuestionFragment newInstance(Question question) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtQuestion=view.findViewById(R.id.txtQuestion);
        RadioGroup radioGroup=view.findViewById(R.id.radioGroup);

        RadioButton radioButton1=view.findViewById(R.id.radioButton1);
        RadioButton radioButton2=view.findViewById(R.id.radioButton2);
        RadioButton radioButton3=view.findViewById(R.id.radioButton3);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                List<String> options = question.getOptions();
                int selectedIndex=0;
                RadioButton selectedRadioButton=view.findViewById(i);
                if(i==R.id.radioButton1)
                {
                    selectedIndex=0;
                }
                if(i==R.id.radioButton2)
                {
                    selectedIndex=1;
                }
                if(i==R.id.radioButton3)
                {
                    selectedIndex=2;
                }
                if(question.getCapital().equals(options.get(selectedIndex)))
                {
                    selectedRadioButton.setBackgroundColor(Color.GREEN);
                    question.setAnswerCorrect(true);
                }
                else
                {
                    selectedRadioButton.setBackgroundColor(Color.RED);
                    question.setAnswerCorrect(false);
                }
                question.setAnswered(true);

                QuizFragment quizFragment= (QuizFragment) getParentFragment();
                quizFragment.onAnswer();
                radioButton1.setEnabled(false);
                radioButton2.setEnabled(false);
                radioButton3.setEnabled(false);

            }
        });

        txtQuestion.setText(question.getQuestion());



        List<String> options=question.getOptions();
        radioButton1.setText("A) "+options.get(0));
        radioButton2.setText("B) "+options.get(1));
        radioButton3.setText("C) "+options.get(2));


    }
}