package com.salton123.saltonframeworkdemo.test

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.salton123.view.adapter.recyclerview.MultiRvAdapter
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
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
    lateinit var mAdapter: RecyclerTestRvAdapter
    override fun getLayout(): Int {
        return R.layout.cp_recycler_test
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        mAdapter = RecyclerTestRvAdapter(_mActivity, R.layout.item_recycler_test)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter.addAll(Arrays.asList("hello", "tom"))
        mAdapter.setOnItemClickListener(object : MultiRvAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                longToast("position=$position" + ",obj=${holder.toString()}")
                start(FragmentDelegate.newInstance(SwipeBackCp::class.java))
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return true;
            }
        })

    }
}