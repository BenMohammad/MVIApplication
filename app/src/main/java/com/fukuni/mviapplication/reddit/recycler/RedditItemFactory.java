package com.fukuni.mviapplication.reddit.recycler;

import com.fukuni.mviapplication.reddit.OnPostClickedListener;
import com.fukuni.mviapplication.reddit.Post;
import com.fukuni.mviapplication.reddit.RedditState;
import com.xwray.groupie.databinding.BindableItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RedditItemFactory {

    public static List<? extends BindableItem> createRecyclerItems(
            RedditState state , OnPostClickedListener listener) {
        if(state instanceof RedditState.Ready)
            return createPostItemList((RedditState.Ready) state, listener);

        else if(state instanceof RedditState.Error)
            return createErrorItemList((RedditState.Error) state);


        return createLoadingItemList();
    }

    private static List<? extends BindableItem> createLoadingItemList() {
        ArrayList<LoadingItem> result = new ArrayList<>(1);
        result.add(new LoadingItem());
        return result;
    }

    private static List<? extends BindableItem> createErrorItemList(
            RedditState.Error state
    ) {
        ArrayList<ErrorItem> result = new ArrayList<>(1);
        result.add(new ErrorItem(state.message()));
        return result;
    }

    private static List<? extends  BindableItem> createPostItemList(
            RedditState.Ready state, OnPostClickedListener listener
    ) {
        ArrayList<PostItem> result = new ArrayList<>(1);
        for(Post post : state.posts()) {
            result.add(new PostItem(post, listener));
        }

        return result;
    }


}
