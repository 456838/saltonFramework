package com.salton123.saltonframeworkdemo.test

import android.content.Context
import com.bumptech.glide.Glide
import com.salton123.view.adapter.base.ViewHolder
import com.salton123.view.adapter.recyclerview.CommonRvAdapter
import com.salton123.saltonframeworkdemo.R

class RecyclerTestRvAdapter(mContext: Context, mLayoutId: Int)
    : CommonRvAdapter<String>(mContext, mLayoutId) {
    override fun convert(holder: ViewHolder, t: String, position: Int) {
        Glide.with(holder.itemView).load(t).into(holder.getView(R.id.ivImage))
    }

}
