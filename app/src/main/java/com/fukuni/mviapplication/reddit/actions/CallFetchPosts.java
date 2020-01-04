package com.fukuni.mviapplication.reddit.actions;

import androidx.annotation.NonNull;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.reddit.RedditSideEffect;
import com.fukuni.mviapplication.reddit.RedditState;
import com.gaumala.mvi.Action;
import com.gaumala.mvi.Update;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CallFetchPosts extends Action<RedditState, RedditSideEffect> {

    public abstract String subredditName();

    @NonNull
    @Override
    public Update<RedditState, RedditSideEffect> update(RedditState state) {
        if(!(state instanceof RedditState.Input))
            return new Update<>(state);

        if(subredditName().isEmpty()) {
            RedditState newState = RedditState.Input.create(
                    R.string.empty_string_error);

            return new Update<>(newState);
        }

        RedditState newState = RedditState.Loading.create(
                subredditName());

        RedditSideEffect sideEffect = RedditSideEffect.FetchPosts.create(
                subredditName());

        return new Update<>(newState, sideEffect);

    }


    public static CallFetchPosts create(String subredditName) {
        return new AutoValue_CallFetchPosts(subredditName);
    }

}
