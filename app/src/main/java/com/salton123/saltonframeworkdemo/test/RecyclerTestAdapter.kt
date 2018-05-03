package com.salton123.saltonframeworkdemo.test

import android.content.Context
import android.widget.TextView
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.saltonframeworkdemo.R

class RecyclerTestAdapter(mContext: Context, mLayoutId: Int)
    : CommonAdapter<String>(mContext, mLayoutId) {

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        setData(holder, data)
    }

    private fun setData(holder: ViewHolder, data: String) {
        holder.getView<TextView>(R.id.tv_test).text = "$data"
    }


}
