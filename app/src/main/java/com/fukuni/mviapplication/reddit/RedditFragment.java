package com.fukuni.mviapplication.reddit;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.util.OnBackPressedListener;

public class RedditFragment extends Fragment implements OnBackPressedListener {


    private RedditGUI gui;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
        }

        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        RedditViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(RedditViewModel.class);
        View view = inflater.inflate(R.layout.reddit_fragment, container, false);
        ActionBar actionBar = setUpActionbar(view.findViewById(R.id.toolbar));

        gui = new RedditGUI(
                this.getViewLifecycleOwner(),
                viewModel.getLiveState(),
                viewModel.getUserActionSink(),
                view, actionBar);
        gui.subscribe();

        return view;
    }

    private ActionBar setUpActionbar(Toolbar toolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        return activity.getSupportActionBar();
    }



    @Override
    public boolean onBackPressed() {
        return gui.onBackPressed();
    }
}
