package com.fukuni.mviapplication.reddit.recycler;

import androidx.annotation.NonNull;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.databinding.LoadingItemBinding;
import com.xwray.groupie.databinding.BindableItem;

public class LoadingItem extends BindableItem<LoadingItemBinding> {

    @Override
    public void bind(@NonNull LoadingItemBinding viewBinding, int position) {

    }

    @Override
    public int getLayout() {
        return R.layout.loading_item;
    }
}
