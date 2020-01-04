package com.fukuni.mviapplication.reddit.recycler;

import androidx.annotation.NonNull;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.databinding.PostItemBinding;
import com.fukuni.mviapplication.reddit.OnPostClickedListener;
import com.fukuni.mviapplication.reddit.Post;
import com.xwray.groupie.databinding.BindableItem;

public class PostItem extends BindableItem<PostItemBinding> {

    private final Post post;
    private final OnPostClickedListener listener;

    PostItem(Post post, OnPostClickedListener listener) {
        this.post = post;
        this.listener = listener;

    }

    @Override
    public void bind(@NonNull PostItemBinding viewBinding, int position) {
        viewBinding.karmaText.setText("" + post.karma());
        viewBinding.titleText.setText("" + post.title());

        viewBinding.getRoot().setOnClickListener(v ->
                listener.onPostClicked(post)
                );
    }

    @Override
    public int getLayout() {
        return R.layout.post_item;
    }
}
