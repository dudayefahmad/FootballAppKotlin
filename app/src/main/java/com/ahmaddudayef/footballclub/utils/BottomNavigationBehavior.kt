package com.ahmaddudayef.footballclub.utils

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * Created by Ahmad Dudayef on 8/9/2018.
 */
class BottomNavigationBehavior<V : View>(context: Context, attrs: AttributeSet) :
        CoordinatorLayout.Behavior<V>(context, attrs) {

    override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: V,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
            coordinatorLayout: CoordinatorLayout,
            child: V,
            target: View,
            dx: Int,
            dy: Int,
            consumed: IntArray,
            type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        child.translationY = Math.max(0f, Math.min(child.height.toFloat(), child.translationY + dy))
    }
}