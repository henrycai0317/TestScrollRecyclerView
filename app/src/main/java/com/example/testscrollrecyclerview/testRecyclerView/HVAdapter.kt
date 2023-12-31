package com.example.testscrollrecyclerview.testRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.BaseViewHolder
import com.example.testscrollrecyclerview.R
import com.example.testscrollrecyclerview.databinding.ViewItemHorizontalVerticalScrollItemBinding

class HVAdapter(private val mData: ArrayList<HRVInfoData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFixXAxis = 0
    private val mMoveViewList = arrayListOf<View>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRCViewHolder {
        return ItemRCViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_item_horizontal_vertical_scroll_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val iInfoData = mData[position]
        (holder as ItemRCViewHolder).mItemView.apply {
            vHeaderLine.visibility = View.VISIBLE
            if (position == 0) {
                vHeaderLine.visibility = View.INVISIBLE
            }
            mDateA.text = iInfoData.dateA
            mUSD.text = iInfoData.priceA
            mPercent.text = iInfoData.percentA
            mAnnounce.text = iInfoData.announce
            mDelivery.text = iInfoData.deliver

            //水平滑動處裡
            llFrameMultipleTopTitleData.scrollTo(mFixXAxis,0)
            mMoveViewList.add(llFrameMultipleTopTitleData)
        }
    }

    fun getMoveViewList():ArrayList<View>{
        return mMoveViewList
    }

    fun setFixXAxis(pXFix:Int){
        mFixXAxis = pXFix
    }

}

class ItemRCViewHolder(val mItemView: ViewItemHorizontalVerticalScrollItemBinding) :
    BaseViewHolder(mItemView.root)