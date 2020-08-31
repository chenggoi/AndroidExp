package com.chenggoi.androidexp.anim

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenggoi.androidexp.R
import com.chenggoi.androidexp.list.ARecyclerAdapter
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_list.*

/**
 *
 * @author chenggoi
 * @since  2020/8/31
 */
class FirstListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val adapter = ARecyclerAdapter()
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }
}