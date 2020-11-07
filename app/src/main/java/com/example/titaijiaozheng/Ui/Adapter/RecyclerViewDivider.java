package com.example.titaijiaozheng.Ui.Adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 间距控制
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    private int space;

    public RecyclerViewDivider(int space) {
        this.space = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = space;
    }
}
