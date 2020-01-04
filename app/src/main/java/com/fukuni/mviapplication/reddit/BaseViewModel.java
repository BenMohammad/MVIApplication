package com.fukuni.mviapplication.reddit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gaumala.mvi.ActionSink;
import com.gaumala.mvi.Dispatcher;

public class BaseViewModel<T, U> extends ViewModel {
    private Dispatcher<T, U> dispatcher = null;

    public BaseViewModel(Dispatcher<RedditState, RedditSideEffect> dispatcher) {
        this.dispatcher = (Dispatcher<T, U>) dispatcher;
    }


    public boolean isInitialized() {
        return dispatcher != null;
    }

    /**
     * setter method for the dispatcher. If it already has a Dispatch
     * instance it does nothing.
     * @param dispatcher
     */
    public void setDispatcher(
            @NonNull Dispatcher<T, U> dispatcher) {
        if (! isInitialized())
            this.dispatcher = dispatcher;
    }

    public ActionSink<T, U> getUserActionSink() {
        return dispatcher;
    }

    @NonNull
    public LiveData<T> getLiveState() {
        return dispatcher.getLiveState();
    }

    /**
     * Calls the dispatcher's {@link Dispatcher#updateSilently(Object)} method.
     * @param state the new state.
     */
    public void updateSilently(T state) {
        dispatcher.updateSilently(state);
    }
}

