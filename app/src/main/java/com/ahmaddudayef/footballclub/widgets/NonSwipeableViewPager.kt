package com.ahmaddudayef.footballclub.widgets

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Ahmad Dudayef on 8/8/2018.
 */
class NonSwipeableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return false
    }
}