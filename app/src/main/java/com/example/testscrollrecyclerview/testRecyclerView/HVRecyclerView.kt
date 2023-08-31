package com.example.testscrollrecyclerview.testRecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
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

    private var mDownX = 0f
    private var mDownY = 0f
    private var mMoveX = 0f
    private var mMoveY = 0f

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

    override fun onInterceptTouchEvent(pEvent: MotionEvent?): Boolean {
        when(pEvent?.action){
            MotionEvent.ACTION_DOWN ->{
                mStartX = pEvent.x
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mViewBinding.apply {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> return true
                MotionEvent.ACTION_MOVE -> {
                    val offsetX = abs(event.x - mStartX).toInt()
                    if (offsetX >= 0) {
                        mMoveOffsetX = (mStartX - event.x + mMaxFixX).toInt()
                        if (0 > mMoveOffsetX) {
                            mMoveOffsetX = 0
                        } else {

                            //當滑動大於最大寬度時，不在滑動(右邊到頭了)
                            if (rightLayoutTotalWidth() + mMoveOffsetX >= llFrameRightTitleLayout.width) {
                                mMoveOffsetX = llFrameRightTitleLayout.width - rightLayoutTotalWidth()
                            }

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
                        vHeaderLine.getLocalVisibleRect(iRect2)
                        llFrameRightTitleLayout.getLocalVisibleRect(iRect)
//                        Log.v("aaa", "mMoveOffsetX=$mMoveOffsetX =llFrameRightTitleLayout=${llFrameRightTitleLayout.width}, ${mViewBinding.llFrameLeftTitle.width}, ${rightLayoutTotalWidth()}")
                        Log.v("aaa", "vHeaderLine=${iRect2.top}, ${iRect2.left}, ${iRect2.right}, ${iRect2.bottom}")
                        Log.v("aaa", "llFrameRightTitleLayout=${iRect.top}, ${iRect.left}, ${iRect.right}, ${iRect.bottom}")
//                        Log.v("aaa", "ia =${iA[0]}, ${iA[1]}, ${iRect.right}, ${iRect.bottom}, vHeaderLine=${vHeaderLine.width}")
//                        Log.v("aaa", "mMoveOffsetX=$mMoveOffsetX =llFrameRightTitleLayout=${llFrameRightTitleLayout.y}")

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
        return super.onTouchEvent(event)

    }

    private fun rightLayoutTotalWidth(): Int {

        return mViewBinding.llFrameRightTitleLayout.width - mViewBinding.llFrameLeftTitle.width
    }
}