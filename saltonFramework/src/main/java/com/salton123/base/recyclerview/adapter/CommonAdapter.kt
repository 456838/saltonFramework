package com.salton123.base.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.hazz.kotlinmvp.view.recyclerview.MultipleType
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemLongClickListener

/**
 * Created by xuhao on 2017/11/22.
 * desc: 通用的 Adapter
 */

abstract class CommonAdapter<T>(var context: Context, var layoutId: Int) : RecyclerView.Adapter<ViewHolder>() {
    protected var mInflater: LayoutInflater? = null
    private var mTypeSupport: MultipleType<T>? = null
    private var mData: MutableList<T> = mutableListOf()
    //使用接口回调点击事件
    private var mItemClickListener: OnItemClickListener? = null

    //使用接口回调点击事件
    private var mItemLongClickListener: OnItemLongClickListener? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    //需要多布局
    constructor(context: Context, typeSupport: MultipleType<T>) : this(context, -1) {
        this.mTypeSupport = typeSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mTypeSupport != null) {
            //需要多布局
            layoutId = viewType
        }
        //创建view
        val view = mInflater?.inflate(layoutId, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemViewType(position: Int): Int {
        //多布局问题
        return mTypeSupport?.getLayoutId(mData[position], position) ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定数据
        bindData(holder, mData[position], position)

//        if (mItemClickListener != null) {
//            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
//        }
//        //长按点击事件
//        if (mItemLongClickListener != null) {
//            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
//        }
        //条目点击事件
        mItemClickListener?.let {
            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
        }

        //长按点击事件
        mItemLongClickListener?.let {
            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
        }
    }

    /**
     * 将必要参数传递出去
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract fun bindData(holder: ViewHolder, data: T, position: Int)

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }


    fun add(item: T) {
        mData.add(mData.size, item)
    }

    fun add(position: Int, item: T) {
        mData.add(position, item)
    }

    fun addAll(collection: MutableList<T>) {
        addAll(mData.size, collection)
    }

    fun addAll(position: Int, collection: MutableList<T>) {
        mData.addAll(position, collection)
        notifyItemRangeInserted(position, collection.size)
    }

    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<T> {
        return mData
    }
}
