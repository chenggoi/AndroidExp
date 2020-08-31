package com.chenggoi.androidexp

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    var mWebView: WebView? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        WebView.setWebContentsDebuggingEnabled(true)
        textview_first.setOnClickListener {
            webview.loadUrl("http://www.wbiw.com/2020/05/13/bedford-man-arrested-after-traffic-stop/")
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
                Log.d(
                    "CG", message + " -- From line "
                            + lineNumber + " of "
                            + sourceID
                );
            }
        }

        mWebView = webview
        webview.webViewClient = Client()
        webview.settings.javaScriptEnabled = true
        webview.settings.databaseEnabled = true
        webview.settings.domStorageEnabled = true
        webview.settings.allowFileAccess = true
        webview.settings.allowFileAccessFromFileURLs = true
        webview.settings.allowContentAccess = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.setAppCacheEnabled(true)
        webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webview.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webview.settings.userAgentString = makeUserAgent(webview.settings.userAgentString)
        webview.settings.textZoom = 100
        webview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        webview.setLayerType(WebView.LAYER_TYPE_NONE, null)
        webview.settings.setSupportMultipleWindows(false)
        webview.settings.javaScriptCanOpenWindowsAutomatically = false
        webview.addJavascriptInterface(JsInterface(), "Android")

        webview.setOnScrollChangeListener(object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                Log.e(
                    "CG",
                    "${scrollX} ${scrollY} ${oldScrollY} ${resources.displayMetrics.density}"
                )
            }

        })
        webview.loadUrl("http://www.wbiw.com/2020/05/13/bedford-man-arrested-after-traffic-stop/")
    }

    private fun makeUserAgent(ua: String): String? {
        val uaBuilder = StringBuilder()
        val extraUa: String =
            "newsbreak" + "/" + BuildConfig.VERSION_NAME
        if (TextUtils.isEmpty(ua)) {
            uaBuilder.append(extraUa)
        } else if (ua.contains(extraUa)) {
            uaBuilder.append(ua)
        } else {
            uaBuilder.append(ua)
            uaBuilder.append(" ")
            uaBuilder.append(extraUa)
        }
        return uaBuilder.toString()
    }


    inner class JsInterface {

        @JavascriptInterface
        public fun showToast(json: String) {


            var obj = JSONObject(json)
            var bottom = obj.getDouble("top") + obj.getDouble("height")
            webview.scrollTo(
                0,
                (bottom * resources.displayMetrics.density - webview.computeVerticalScrollExtent()).toInt()
            )
            Log.e(
                "CG", " " + (bottom * resources.displayMetrics.density).toInt() + " " +
                        json.toString() + " " + webview.computeVerticalScrollExtent() + " " + webview.computeVerticalScrollRange() + " " + webview.computeVerticalScrollOffset()
            )

        }
    }

    class Client : WebViewClient() {
        val js =
            "function getContentPosition(){console.log(\">>>>>\",\"start run\");console.log(\">>>>>\",\"param is: \",window.TestReadData);console.log(\">>>>>\",\"message poster is: \",window.postMessage);const xpath=window.TestReadData.XPath;const xpathResulte=document.evaluate(xpath,document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null);console.log(\">>>>>\",\"xpathreasulte is: \",xpathResulte);const node=xpathResulte.singleNodeValue;console.log(\">>>>>\",\"the target node is: \",node);const position=getNodePosition(node);console.log(\">>>>>\",\"position is: \",position);Android.showToast(JSON.stringify(position))}function getNodePosition(node){const rect=node.getBoundingClientRect();const scrollTop=window.scrollY;const scrollLeft=window.scrollX;return{width:rect.width,height:rect.height,left:rect.left+scrollLeft,top:rect.top+scrollTop,}}console.log(\"aaa\");getContentPosition();"

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            var jss =
                "window.TestReadData={XPath:'" + "/html/body/div[4]/div/div[5]/div/div[1]/div/div/div[2]/article/div/div[3]/p[7]" + "',contentLastWords:'" + "Beverstock, of 896 State Road 58 East, is facing charges of possession of meth, unlawful possession of a syringe, resisting arrest, and possession of drug paraphernalia." + "'};" + js
            var jss2 =
                "javascript:(function(){" + jss + "})()"
            Log.e("CG", "finish" + view)
            view?.evaluateJavascript(jss2, null)
        }
    }
}