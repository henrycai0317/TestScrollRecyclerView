package com.example.testscrollrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testscrollrecyclerview.databinding.ActivityMainBinding
import com.example.testscrollrecyclerview.projectDemo.CoinActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mLeftList = arrayListOf<String>()
    private val mTopList = arrayListOf<String>()
    private val mRightList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

        startActivity(Intent(this@MainActivity, CoinActivity::class.java))
//        initView()
//        listener()
//        initRecyclerView()
    }

    private fun initRecyclerView() {

    }

    private fun initView() {

        for (i in 0 until 9) {
            mTopList.add("第 ${i + 1} 列")
        }

        for (i in 0 until 450) {
            mRightList.add("item ${i + 1}")
        }

        for (i in 0 until 50) {
            mLeftList.add("row ${i + 1}")
        }

        mBinding.apply {
            recyclerViewLeft.adapter = ScrollItemAdapter(mLeftList)
            rightRecyclerViewTitle.adapter = ScrollItemAdapter(mTopList)
            rightRecyclerViewContent.adapter = ScrollItemAdapter(mRightList)
        }


    }

    private fun listener() {
        mBinding.apply {

        }
    }

}


