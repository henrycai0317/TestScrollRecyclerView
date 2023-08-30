package com.example.testscrollrecyclerview.testRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.BaseViewHolder
import com.example.testscrollrecyclerview.R
import com.example.testscrollrecyclerview.databinding.ViewItemHorizontalVerticalScrollItemBinding

class HVAdapter(val mDatas: ArrayList<HRVInfoData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRCViewHolder {
        return ItemRCViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_item_horizontal_vertical_scroll_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val iInfoData = mDatas[position]
        (holder as ItemRCViewHolder).mItemView.apply {
            mDateA.text = iInfoData.dateA
            mUSD.text = iInfoData.priceA
            mPercent.text = iInfoData.percentA
            mAnnounce.text = iInfoData.announce
            mDelivery.text = iInfoData.deliver
        }
    }
}

class ItemRCViewHolder(val mItemView: ViewItemHorizontalVerticalScrollItemBinding) :
    BaseViewHolder(mItemView.root)