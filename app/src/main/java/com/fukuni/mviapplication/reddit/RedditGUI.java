package com.fukuni.mviapplication.reddit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.reddit.actions.CallFetchPosts;
import com.fukuni.mviapplication.reddit.actions.ResetForm;
import com.fukuni.mviapplication.reddit.recycler.RedditItemFactory;
import com.fukuni.mviapplication.util.Animations;
import com.gaumala.mvi.ActionSink;
import com.gaumala.mvi.BaseUI;
import com.google.android.material.textfield.TextInputLayout;
import com.xwray.groupie.GroupAdapter;

class RedditGUI extends BaseUI<RedditState> {

    private final Context context;
    private final ActionSink<RedditState, RedditSideEffect> sink;
    private final ActionBar actionBar;

    private final View inputForm;
    private final TextInputLayout subredditInputLayout;
    private final View submitButton;
    private final GroupAdapter postsAdapter;
    private final RecyclerView postsRecycler;
    private final Toolbar toolbar;

    RedditGUI (@NonNull LifecycleOwner owner,
               @NonNull LiveData<RedditState> liveState,
               ActionSink<RedditState, RedditSideEffect> sink,
               View view,
               ActionBar actionBar) {
        super(owner, liveState);
        this.context = view.getContext();
        this.sink = sink;
        this.actionBar =actionBar;
        this.postsAdapter = new GroupAdapter();

        inputForm = view.findViewById(R.id.subreddit_input_form);
        subredditInputLayout = view.findViewById(R.id.subreddit_input_layout);
        submitButton = view.findViewById(R.id.submit_button);
        postsRecycler = view.findViewById(R.id.posts_recycler);
        toolbar = view.findViewById(R.id.toolbar);

        setUpRecyclers();
    }

    @Override
    public void rebind(RedditState state) {
      toolbar.setTitle(getTitle(state));

        if(state instanceof RedditState.Input)
            showInputForm((RedditState.Input) state);
        else
            showPostsRecycler();

        postsAdapter.update(RedditItemFactory.createRecyclerItems(
                state, p -> openLink(p.url())));
    }


    private void showPostsRecycler() {
        Animations.swicthViewWithHorizontalSlide(
                inputForm, postsRecycler, false);

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void openLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    private void showInputForm(RedditState.Input state) {
        Animations.swicthViewWithHorizontalSlide(
                postsRecycler, inputForm, false);

        actionBar.setDisplayHomeAsUpEnabled(true);
        if(state.errorResId() != -1)
            subredditInputLayout.setError(context.getString(state.errorResId()));

        submitButton.setOnClickListener(v -> {
            String inputText = subredditInputLayout.getEditText().getText().toString();

            sink.submitAction(CallFetchPosts.create(inputText));
        });
    }


    private String getTitle(RedditState state) {
        if(state instanceof RedditState.Loading)
            return "/r/" + ((RedditState.Loading) state).subredditName();

        if(state instanceof RedditState.Ready)
            return "/r/" + ((AutoValue_RedditState_Ready) state).subredditName();


        return context.getString(R.string.reddit);
    }

    private void setUpRecyclers() {
        postsRecycler.setAdapter(postsAdapter);

        DividerItemDecoration divider =
                new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        postsRecycler.addItemDecoration(divider);

    }

    boolean onBackPressed() {
        RedditState state = liveState.getValue();
        if(state instanceof RedditState.Input)
            return false;
        else {
            subredditInputLayout.getEditText().setText("");
            sink.submitAction(ResetForm.create());
            return true;
        }
    }

}
