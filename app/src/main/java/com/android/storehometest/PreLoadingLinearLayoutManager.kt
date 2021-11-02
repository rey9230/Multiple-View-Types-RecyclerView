package com.android.storehometest

import android.content.Context
import androidx.recyclerview.widget.OrientationHelper

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager



class PreLoadingLinearLayoutManager : LinearLayoutManager {
    private var mPages = 1
    private var mOrientationHelper: OrientationHelper? = null

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, pages: Int) : super(context) {
        mPages = pages
    }

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
    }

    override fun setOrientation(orientation: Int) {
        super.setOrientation(orientation)
        mOrientationHelper = null
    }

    /**
     * Set the number of pages of layout that will be preloaded off-screen,
     * a page being a pixel measure equivalent to the on-screen size of the
     * recycler view.
     * @param pages the number of pages; can be `0` to disable preloading
     */
    fun setPages(pages: Int) {
        mPages = pages
    }

    override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
        if (mOrientationHelper == null) {
            mOrientationHelper = OrientationHelper.createOrientationHelper(this, orientation)
        }
        return mOrientationHelper!!.totalSpace * mPages
    }
}