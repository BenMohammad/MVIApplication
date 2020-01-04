package com.fukuni.mviapplication.reddit;

import com.gaumala.mvi.Dispatcher;


public class RedditViewModel extends BaseViewModel<RedditState, RedditSideEffect> {

    RedditViewModel(Dispatcher<RedditState, RedditSideEffect> dispatcher) {
        super(dispatcher);
    }
}
