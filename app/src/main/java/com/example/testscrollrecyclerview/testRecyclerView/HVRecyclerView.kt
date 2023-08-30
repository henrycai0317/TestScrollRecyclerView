package com.example.testscrollrecyclerview.testRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.databinding.ViewHorizontalVarticalScrollRecyclerBinding

class HVRecyclerView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private val mViewBinding: ViewHorizontalVarticalScrollRecyclerBinding by lazy {
        ViewHorizontalVarticalScrollRecyclerBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }


    fun setAdapter(pAdapter: HVAdapter) {
        mViewBinding.apply {
            moveRecyclerView.adapter = pAdapter
        }
        verticalShadowControl()
    }

    private fun verticalShadowControl() {
        mViewBinding.apply {
            moveRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (moveRecyclerView.canScrollVertically(-1)) {
                        vHorizontalShadow.visibility = VISIBLE
                    } else {
                        vHorizontalShadow.visibility = INVISIBLE
                    }
                }
            })
        }
    }

}