package com.salton123.saltonframeworkdemo.test

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.base.BaseSupportFragment
import com.salton123.saltonframeworkdemo.R
import kotlinx.android.synthetic.main.cp_recycler_test.*
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/3 下午5:06
 * ModifyTime: 下午5:06
 * Description:
 */
class RecyclerTestComponent : BaseSupportFragment() {
    lateinit var mAdapter: RecyclerTestAdapter
    override fun getLayout(): Int {
        return R.layout.cp_recycler_test
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        mAdapter = RecyclerTestAdapter(context, R.layout.item_recycler_test)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter.addAll(Arrays.asList("hello", "tom"))
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                longToast("position=$position" + ",obj=${obj.toString()}")
            }
        })

    }
}