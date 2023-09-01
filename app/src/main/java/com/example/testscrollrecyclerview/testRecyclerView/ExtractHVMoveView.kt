package com.example.testscrollrecyclerview.testRecyclerView

import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.databinding.ViewHorizontalVarticalScrollRecyclerBinding
import kotlin.math.abs

class ExtractHVMoveView (private val mViewBinding:ViewHorizontalVarticalScrollRecyclerBinding){
    private var mHorizontalMoveViewList = arrayListOf<View>()
    //最大可滑動差值
    private var mMaxFixX = 0
    //手指按下和滑動時的差值
    private var mMoveOffsetX = 0
    //手指按下時的位置
    private var mStartX = 0f
    private var mMoveNow = false
    private var mTotalWidth = -1
    private var mMaxMove = -1

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
                        vHorizontalShadow.visibility = RelativeLayout.VISIBLE
                    } else {
                        vHorizontalShadow.visibility = RelativeLayout.INVISIBLE
                    }
                }
            })
        }
    }


    fun setTouchMoveNow(pEvent: MotionEvent) {
        if (!mMoveNow) {
            mStartX = pEvent.x
            if (mTotalWidth == -1) {
                mViewBinding.apply {
                    llFrameRightTitleLayout.measure(0, 0)
                    mTotalWidth = llFrameRightTitleLayout.measuredWidth
                    mMaxMove = mTotalWidth - vHeaderLine.width
                }

            }
        }
        onTouchEvent(pEvent)
        mMoveNow = true
    }

    fun setTouchUp(pEvent: MotionEvent) {
        if (mMoveNow) {
            onTouchEvent(pEvent)
        }
        mMoveNow = false
    }

    private fun onTouchEvent(event: MotionEvent?) {
        mViewBinding.apply {
            when (event?.action) {
                MotionEvent.ACTION_MOVE -> {
                    val offsetX = abs(event.x - mStartX).toInt()
                    if (offsetX >= 0) {
                        mMoveOffsetX = (mStartX - event.x + mMaxFixX).toInt()
                        if (0 > mMoveOffsetX) {
                            mMoveOffsetX = 0
                        } else {

                            //當滑動大於最大寬度時，不在滑動(右邊到頭了)

//                            if (rightLayoutTotalWidth() + mMoveOffsetX >= llFrameRightTitleLayout.width) {
//                                mMoveOffsetX = llFrameRightTitleLayout.width - rightLayoutTotalWidth()
//                            }


                            if (vHeaderLine.width + mMoveOffsetX >= mTotalWidth) {
                                mMoveOffsetX = mMaxMove
                            }

                            Log.v("aaa", "Uitils.mDeviceWidth=${Uitils.mDeviceWidth} =vHeaderLine=${vHeaderLine.width}, iTotal=${mTotalWidth}, " +
                                    "llFrameMultipleTopTitle=${llFrameMultipleTopTitle.width}, llFrameRightTitleLayout=${llFrameRightTitleLayout.width}, mTotalWidth=${mTotalWidth}, mMoveOffsetX=${mMoveOffsetX}")
                        }
                        //跟随手指向右滚动
                        llFrameRightTitleLayout.scrollTo(mMoveOffsetX, 0)
                        if (mHorizontalMoveViewList.isNotEmpty()) {
                            for (i in mHorizontalMoveViewList.indices) {
                                //使每个item随着手指向右滚动
                                mHorizontalMoveViewList[i].scrollTo(mMoveOffsetX, 0)
                            }
                        }
                        val iRect = Rect()
                        val iA = IntArray(2)
//                        llFrameRightTitleLayout.getLocationOnScreen(iA)
//                        llFrameRightTitleLayout.getLocationInWindow(iA)
//                        llFrameRightTitleLayout.getLocalVisibleRect(iRect)
                        val iRect2 = Rect()
                        tvTitleE.getLocalVisibleRect(iRect2)
                        llFrameRightTitleLayout.getLocalVisibleRect(iRect)
//                        Log.v("aaa", "mMoveOffsetX=$mMoveOffsetX =llFrameRightTitleLayout=${llFrameRightTitleLayout.width}, ${mViewBinding.llFrameLeftTitle.width}, ${rightLayoutTotalWidth()}")
                        Log.v("aaa", "tvTitleE=${iRect2.top}, ${iRect2.left}, ${iRect2.right}, ${iRect2.bottom}")
                        Log.v("aaa", "llFrameRightTitleLayout=${iRect.top}, ${iRect.left}, ${iRect.right}, ${iRect.bottom}")
//                        Log.v("aaa", "ia =${iA[0]}, ${iA[1]}, ${iRect.right}, ${iRect.bottom}, vHeaderLine=${vHeaderLine.width}")


                        rlShadowHeaderVertical.visibility = View.INVISIBLE
                        if (iRect.left > 0) {
                            rlShadowHeaderVertical.visibility = View.VISIBLE
                        }
                    }
                }

                MotionEvent.ACTION_UP -> {
                    mMaxFixX = mMoveOffsetX  //設置水平最大平移的寬度
                    //每次左、右滑動都要更新Adapter中的mFix數值，讓它移動到目前滑道的距離
                    //每次左右滑动都要更新CommonAdapter中的mFixX的值
                    val iAdapter = moveRecyclerView.adapter
                    if (iAdapter != null && iAdapter is HVAdapter) {
                        iAdapter.setFixXAxis(mMaxFixX)
                    }

                }
            }
        }
    }

    private fun rightLayoutTotalWidth(): Int {

        // mViewBinding.llFrameRightTitleLayout.width 這個寬 跟螢幕依樣

        return mViewBinding.llFrameRightTitleLayout.width - mViewBinding.llFrameLeftTitle.width
    }





}