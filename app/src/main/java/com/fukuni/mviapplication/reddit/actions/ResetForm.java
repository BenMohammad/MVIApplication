package com.fukuni.mviapplication.reddit.actions;

import androidx.annotation.NonNull;

import com.fukuni.mviapplication.reddit.RedditSideEffect;
import com.fukuni.mviapplication.reddit.RedditState;
import com.gaumala.mvi.Action;
import com.gaumala.mvi.Update;
import com.google.auto.value.AutoValue;

@AutoValue
public class ResetForm extends Action<RedditState, RedditSideEffect> {



    @NonNull
    @Override
    public Update<RedditState, RedditSideEffect> update(RedditState state) {
        return new Update<>(RedditState.Input.create(-1));
    }

    public static ResetForm create() {
        return new AutoValue_ResetForm();
    }

}
