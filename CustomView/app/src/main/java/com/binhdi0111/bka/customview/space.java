package com.binhdi0111.bka.customview;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class space extends RecyclerView.ItemDecoration {
    private final int vertical;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = vertical;
        super.getItemOffsets(outRect, view, parent, state);
    }

    public space(int vertical) {
        this.vertical = vertical;
    }
}
