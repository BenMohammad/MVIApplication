package com.fukuni.mviapplication.reddit.recycler;

import androidx.annotation.NonNull;

import com.fukuni.mviapplication.R;
import com.fukuni.mviapplication.databinding.ErrorItemBinding;
import com.xwray.groupie.databinding.BindableItem;

public class ErrorItem extends BindableItem<ErrorItemBinding> {

    private final String message;

    public ErrorItem(String message) {
        this.message = message;
    }

    @Override
    public void bind(@NonNull ErrorItemBinding viewBinding, int position) {
        viewBinding.errorText.setText(message);
    }

    @Override
    public int getLayout() {
        return R.layout.error_item;
    }
}
