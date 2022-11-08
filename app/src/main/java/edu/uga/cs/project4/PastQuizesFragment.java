package edu.uga.cs.project4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PastQuizesFragment extends Fragment {



    private RecyclerView recyclerView;
    ProjectDBHelper projectDBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_quizes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        projectDBHelper=ProjectDBHelper.getInstance(requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        new AsyncTask<Void, List<Quiz>>() {
            @Override
            protected List<Quiz> doInBackground(Void... arguments) {
                return projectDBHelper.getSavedQuizResults();
            }

            @Override
            protected void onPostExecute(List<Quiz> quizzes) {

                recyclerView.setAdapter(new QuizReviewAdapter(quizzes,requireContext()));

            }
        }.execute();
    }
}