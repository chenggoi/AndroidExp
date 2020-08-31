package com.chenggoi.androidexp.webview

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 *
 * @author chenggoi
 * @since  2020/7/23
 */
class CustomWebView : WebView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    override public fun computeVerticalScrollRange(): Int {
        return super.computeVerticalScrollRange()
    }

    override public fun computeVerticalScrollOffset(): Int {
        return super.computeVerticalScrollOffset()
    }

    override public fun computeVerticalScrollExtent(): Int {
        return super.computeVerticalScrollExtent()
    }
}