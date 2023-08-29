package com.example.testscrollrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testscrollrecyclerview.databinding.ItemTextGridBinding

class ScrollItemAdapter(val mList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_text_grid, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val iName = mList[position]
//        (holder as ItemViewHolder).mItemView.tvItem.text = iName
    }

    override fun getItemCount(): Int {
        return mList.size
    }


}

class ItemViewHolder(mItemView: ItemTextGridBinding) : BaseViewHolder(mItemView.root)

