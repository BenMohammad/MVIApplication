package com.fukuni.mviapplication.reddit;

import androidx.annotation.StringRes;

import com.google.auto.value.AutoValue;

import java.util.List;

public abstract class RedditState {

    @AutoValue
    public static abstract class Input extends RedditState {
        public abstract @StringRes
        int errorResId();

        public static Input create(int errorResId) {
            return new AutoValue_RedditState_Input(errorResId);
        }
    }

    @AutoValue
    public static abstract class Loading extends RedditState {
        public abstract String subredditName();

        public static Loading create(String subredditName) {
            return new AutoValue_RedditState_Loading(subredditName);
        }
    }

    @AutoValue
    public static abstract class Ready extends RedditState {
        public abstract String subredditName();
        public abstract List<Post> posts();

        public static
        Ready create(String subredditName, List<Post> posts) {
            return new AutoValue_RedditState_Ready(subredditName, posts);
        }
    }


    @AutoValue
    public static abstract class Error extends RedditState {
        public abstract String message();

        public static Error create(String message) {
            return new AutoValue_RedditState_Error(message);
        }
    }

    public static RedditState createInitialState() {
        return Input.create(-1);
    }


}
