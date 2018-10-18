package com.ahmaddudayef.flux.ui.home.tv

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Ahmad Dudayef on 8/9/2018.
 */
class ItemOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State?
    ) {
        if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left = offset / 2
            outRect.right = offset
        }
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right = offset / 2
            outRect.left = offset
        }

        outRect.bottom = offset * 3
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = offset
        if (parent.getChildAdapterPosition(view) == 1)
            outRect.top = offset
    }
}