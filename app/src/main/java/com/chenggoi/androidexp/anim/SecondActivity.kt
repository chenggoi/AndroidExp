package com.chenggoi.androidexp.anim

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.chenggoi.androidexp.R
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

/**
 *
 * @author chenggoi
 * @since  2020/8/31
 */
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).setTransitionName("feed_card")
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        val enterTransform = MaterialContainerTransform()
        enterTransform.addTarget(android.R.id.content)
        enterTransform.setDuration(300L)
        window.sharedElementEnterTransition = enterTransform
        val exitTransform = MaterialContainerTransform()
        exitTransform.addTarget(android.R.id.content)
        exitTransform.setDuration(250L)
        window.sharedElementReturnTransition = exitTransform

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }
}