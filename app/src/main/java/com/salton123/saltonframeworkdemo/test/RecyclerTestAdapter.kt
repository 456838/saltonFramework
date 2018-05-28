package com.salton123.saltonframeworkdemo.test

import android.content.Context
import android.widget.ImageView
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.saltonframeworkdemo.R

class RecyclerTestAdapter(mContext: Context, mLayoutId: Int)
    : CommonAdapter<String>(mContext, mLayoutId) {

    init {
    }
    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        setData(holder, data)
    }

    private fun setData(holder: ViewHolder, data: String) {
//        holder.getView<TextView>(R.id.tv_test).text = "$data"
        holder.setImagePath(R.id.ivImage, object : ViewHolder.HolderImageLoader(data) {
            override fun loadImage(iv: ImageView, path: String) {
            }
        })
    }


}
