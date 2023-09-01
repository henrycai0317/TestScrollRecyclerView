package com.example.testscrollrecyclerview.testRecyclerView


import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import kotlin.math.abs

class HVMoveUseRelativeLayout(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {



    private var mDownX = 0f
    private var mDownY = 0f
    private var mMoveX = 0f
    private var mMoveY = 0f

    override fun onInterceptTouchEvent(pEvent: MotionEvent?): Boolean {
        when(pEvent?.action){
            MotionEvent.ACTION_DOWN ->{

                mDownX = pEvent.x
                mDownY = pEvent.y
            }

            MotionEvent.ACTION_MOVE-> {
                mMoveX = pEvent.x
                mMoveY = pEvent.y
                return abs(mDownX - mMoveX) >= abs((mDownY - mMoveY))
            }
        }
        return super.onInterceptTouchEvent(pEvent)
    }


}