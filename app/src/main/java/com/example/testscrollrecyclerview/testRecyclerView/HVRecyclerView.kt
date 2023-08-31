package com.example.testscrollrecyclerview.testRecyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.databinding.ViewHorizontalVarticalScrollRecyclerBinding
import kotlin.math.abs

class HVRecyclerView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private var mHorizontalMoveViewList = arrayListOf<View>()
    //最大可滑動差值
    private var mMaxFixX = 0
    //手指按下和滑動時的差值
    private var mMoveOffsetX = 0
    //手指按下時的位置
    private var mStartX = 0f
    //觸發攔截手勢的最小值
    private val mTriggerMoveDis = 30

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
            mHorizontalMoveViewList = pAdapter.getMoveViewList()
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

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                mStartX = ev.x
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = abs(ev.x - mStartX).toInt()
                //水平移動大於30觸發攔截
                return offsetX > mTriggerMoveDis
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> return true
            MotionEvent.ACTION_MOVE -> {
//                val offsetX = abs(event.x - mStartX).toInt()
//                if (offsetX > 30) {
//                    mMoveOffsetX = (mStartX - event.x + mMaxFixX).toInt()
//                    if (0 > mMoveOffsetX) {
//                        mMoveOffsetX = 0
//                    } else {
//                        //当滑动大于最大宽度时，不在滑动（右边到头了）
//                        if (mRightTitleLayout.getWidth() + mMoveOffsetX > rightTitleTotalWidth()) {
//                            mMoveOffsetX = rightTitleTotalWidth() - mRightTitleLayout.getWidth()
//                        }
//                    }
//                    //跟随手指向右滚动
//                    mRightTitleLayout.scrollTo(mMoveOffsetX, 0)
//                    if (null != mMoveViewList) {
//                        for (i in mMoveViewList.indices) {
//                            //使每个item随着手指向右滚动
//                            mMoveViewList.get(i).scrollTo(mMoveOffsetX, 0)
//                        }
//                    }
//                    if (0 == mMoveOffsetX) {
//                        mLeftTitleLayout.setElevation(0f)
//                    } else {
//                        mLeftTitleLayout.setElevation(20f)
//                    }
//                    if (null != mHeadViewList) {
//                        for (i in mHeadViewList.indices) {
//                            if (0 == mMoveOffsetX) {
//                                mHeadViewList.get(i).setElevation(0f)
//                            } else {
//                                mHeadViewList.get(i).setElevation(20f)
//                            }
//                        }
//                    }
//                }
            }
            MotionEvent.ACTION_UP -> {
                mMoveOffsetX
            }
        }
        return super.onTouchEvent(event)

    }
}