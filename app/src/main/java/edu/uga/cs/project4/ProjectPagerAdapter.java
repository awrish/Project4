package edu.uga.cs.project4;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProjectPagerAdapter extends FragmentStateAdapter {

    public ProjectPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }

    @Override
    public Fragment createFragment(int position) {
        return NewQuizFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return NewQuizFragment.getNumberOfQuestions();
    }

}
