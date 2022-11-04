package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class NewQuizFragment extends Fragment {

    public static final String TAG = "NewQuizFragment";


    public NewQuizFragment() {
        //required empty public constructor
    }

    public static NewQuizFragment newInstance() {
        NewQuizFragment fragment = new NewQuizFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    // read the values from SQL and initalize them here ???

}
