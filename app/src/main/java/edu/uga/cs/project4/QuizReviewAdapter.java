package edu.uga.cs.project4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * QuizReviewAdapter for the class. Used to display the prevoiusly taken quizzes and attributes
 * regarding them.
 */
public class QuizReviewAdapter extends RecyclerView.Adapter<QuizReviewAdapter.ViewHolder> {
    private List<Quiz> quizList;
    Context context;
    public QuizReviewAdapter(List<Quiz> quizList, Context context) {
        this.quizList = quizList;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_quiz,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {

        Quiz quiz=quizList.get(i);
        holder.txtDate.setText(quiz.getDate());
        holder.txtAnswered.setText("Answered : "+quiz.getQuestionAnswered());
        holder.txtNotAnswered.setText("Not Answered : "+(6-Integer.parseInt(quiz.getQuestionAnswered())));
        holder.txtScore.setText("Score : "+quiz.getScore());
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTotalQuestion,txtDate,txtAnswered,txtNotAnswered,txtScore;
        public ViewHolder(View view) {
            super(view);

            txtTotalQuestion=view.findViewById(R.id.txtTotalQuestion);
            txtDate=view.findViewById(R.id.txtDate);
            txtAnswered=view.findViewById(R.id.txtAnswered);
            txtNotAnswered=view.findViewById(R.id.txtNotAnswered);
            txtScore=view.findViewById(R.id.txtScore);


        }
    }
}
