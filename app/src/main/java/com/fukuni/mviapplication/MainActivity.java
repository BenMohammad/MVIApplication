package com.fukuni.mviapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.fukuni.mviapplication.reddit.RedditFragment;
import com.fukuni.mviapplication.util.OnBackPressedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();

    }

    private void setUpInitialFragment(FragmentManager fm) {
        Fragment fragment = new RedditFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.root_layout, fragment);
        transaction.commit();
    }

    private void setUpUI() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.root_layout) != null) {
            return;
        }
        setUpInitialFragment(fm);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.root_layout);

        if(!(currentFragment instanceof OnBackPressedListener)) {
            super.onBackPressed();
            return;
        }

        boolean shouldSwallowBackEvent =
                ((OnBackPressedListener) currentFragment).onBackPressed();

        if(!shouldSwallowBackEvent)
            super.onBackPressed();
    }
}
