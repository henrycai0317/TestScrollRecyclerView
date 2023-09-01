package com.example.testscrollrecyclerview.testRecyclerView

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.testscrollrecyclerview.databinding.ViewHorizontalVarticalScrollRecyclerBinding
import kotlin.math.abs

class ActivityRecyclerMoveHAndV: AppCompatActivity() {


//    private var mDownX = 0f
//    private var mDownY = 0f
//    private var mMoveX = 0f
//    private var mMoveY = 0f
    private val mTestHRVData = arrayListOf<HRVInfoData>()
    private lateinit var mBinding: ViewHorizontalVarticalScrollRecyclerBinding
    private val mExtractHVMoveView: ExtractHVMoveView by lazy {
        ExtractHVMoveView(mBinding)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ViewHorizontalVarticalScrollRecyclerBinding.inflate(layoutInflater)
        setContentView(mBinding.root)



        for (i in 0 until 500) {
            val iInfoData = HRVInfoData("2023/08/30", "0.0124", "6.38%", "2023/07/25", "2023/05/22")
            mTestHRVData.add(iInfoData)
        }

        initRecyclerView()

        mBinding.moveRecyclerView.setOnTouchListener { _, pEvent ->
            Log.v("aaa", "xxxxxxxxxxxxxxxxxxxxxx")
            false
        }
        mBinding.rtFrame.setOnTouchListener { _, pEvent ->
            when(pEvent?.action){
                MotionEvent.ACTION_DOWN ->{
                }

                MotionEvent.ACTION_MOVE-> {
                    mExtractHVMoveView.setTouchMoveNow(pEvent)
                }
                MotionEvent.ACTION_UP-> {
                    mExtractHVMoveView.setTouchUp(pEvent)
                }
            }
            false
        }


    }

    private fun initRecyclerView() {
        mBinding.apply {
            mExtractHVMoveView.setAdapter(HVAdapter(mTestHRVData))
        }
    }
}