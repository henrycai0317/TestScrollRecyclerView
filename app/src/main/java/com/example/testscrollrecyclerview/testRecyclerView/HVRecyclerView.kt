package com.example.testscrollrecyclerview.testRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
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
    }

}