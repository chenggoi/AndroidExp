package com.chenggoi.androidexp.list

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chenggoi.androidexp.anim.SecondActivity

/**
 *
 * @author chenggoi
 * @since  2020/8/31
 */
class AViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView)

    public fun bind() {
        itemView.setOnClickListener {
            itemView.context.startActivity(
                Intent(itemView.context, SecondActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    it,
                    "feed_card"
                ).toBundle()
            )
        }
    }
}