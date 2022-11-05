package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class NewQuizFragment extends Fragment {

    public static final String TAG = "NewQuizFragment";

    private int questionNum; //which question to display in fragment


    public NewQuizFragment() {
        //required empty public constructor
    }

    public static NewQuizFragment newInstance( int questionNum ) {
        NewQuizFragment fragment = new NewQuizFragment();
        Bundle args = new Bundle();
        args.putInt( "questionNum", questionNum );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    // read the values from SQL and initalize them here ???
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        TextView questionView = view.findViewById( R.id.textView );
        RadioButton question1 = view.findViewById( R.id.radioButton );
        RadioButton question2 = view.findViewById( R.id.radioButton2 );
        RadioButton question3 = view.findViewById( R.id.radioButton3 );
    }

    public static int getNumberOfQuestions() { return 6; }


}
