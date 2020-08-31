package com.chenggoi.androidexp.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chenggoi.androidexp.R
import com.chenggoi.androidexp.bean.ABean

/**
 *
 * @author chenggoi
 * @since  2020/8/31
 */
class ARecyclerAdapter : RecyclerView.Adapter<AViewHolder>() {

    val mSource = arrayListOf<ABean>(
        ABean(), ABean(), ABean(), ABean(), ABean(), ABean(), ABean(), ABean(), ABean(), ABean(),
        ABean()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AViewHolder {
        Log.e("CG","aaaa ${getItemCount()}")
        return AViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_a_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return mSource.size
    }
}