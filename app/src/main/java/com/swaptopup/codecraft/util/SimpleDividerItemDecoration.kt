package com.swaptopup.codecraft.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.swaptopup.codecraft.R

class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable

    init {
        mDivider = context.getResources().getDrawable(R.drawable.divider)
    }

    @Override
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.getPaddingLeft()
        val right = parent.getWidth() - parent.getPaddingRight()

        val childCount = parent.getChildCount()
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.getLayoutParams() as RecyclerView.LayoutParams

            val top = child.getBottom() + params.bottomMargin
            val bottom = top + mDivider.getIntrinsicHeight()

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}