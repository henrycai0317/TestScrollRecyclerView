package com.example.testscrollrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testscrollrecyclerview.databinding.ActivityMainBinding
import com.example.testscrollrecyclerview.projectDemo.CoinActivity
import com.example.testscrollrecyclerview.testRecyclerView.HRVInfoData
import com.example.testscrollrecyclerview.testRecyclerView.HVAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mTestHRVData = arrayListOf<HRVInfoData>()
    private val mLeftList = arrayListOf<String>()
    private val mTopList = arrayListOf<String>()
    private val mRightList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

//        startActivity(Intent(this@MainActivity, CoinActivity::class.java))
        initView()
        initRecyclerView()
//        listener()
    }

    private fun initView() {
        for (i in 0 until 500) {
            val iInfoData = HRVInfoData("2023/08/30", "0.0124", "6.38%", "2023/07/25", "2023/05/22")
            mTestHRVData.add(iInfoData)
        }

    }
    private fun initRecyclerView() {
        mBinding.apply {
            recyclerView.setAdapter(HVAdapter(mTestHRVData))
        }
    }

    private fun listener() {
        mBinding.apply {

        }
    }

}


