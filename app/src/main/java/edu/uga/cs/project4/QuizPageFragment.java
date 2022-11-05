package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class QuizPageFragment extends Fragment {

    //inflate the layout of view pager here
    //do what was done in previous main activity

    public QuizPageFragment() {}

    public static QuizPageFragment newInstance() {
        QuizPageFragment quizPageFragment = new QuizPageFragment();
        return quizPageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        //public void onActivityCreated(Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        ViewPager2 pager = view.findViewById( R.id.viewpager );
        ProjectPagerAdapter avpAdapter = new
                ProjectPagerAdapter(
                getChildFragmentManager(), getLifecycle() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );
    }



}
