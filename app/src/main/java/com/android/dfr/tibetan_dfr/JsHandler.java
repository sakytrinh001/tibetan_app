package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by HanTH2 on 11/6/17.
 * Class to handle all calls from JS & from Java too
 */

public class JsHandler {

    Activity activity;
    String TAG = "JsHandler";
    WebView webView;

    public JsHandler(Activity _contxt, WebView _webView) {
        activity = _contxt;
        webView = _webView;
    }

    /**
     * This function handles call from JS
     */
    @JavascriptInterface
    public void jsCallToAndroid(String jsString) {
        showDialog(jsString);
    }

    /**
     * This function handles call from Android-Java
     */

    public void androidCallToJSChangeFontStyle(String fontStyle) {
        final String webUrl = "javascript:androidCallToJSChangeFontStyle('"+fontStyle+"')";
        if(!activity.isFinishing()) {
            // loadurl on UI main thread
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl(webUrl);
                    webView.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void anddroidCallToJSChangeFontSize(int fontSize) {
        final String webUrl = "javascript:anddroidCallToJSChangeFontSize('"+fontSize+"')";
        if(!activity.isFinishing()) {
            // loadurl on UI main thread
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl(webUrl);
                }
            });
        }
    }

    public void anddroidCallToJSChangeBackgroundColor(String color) {
        final String webUrl = "javascript:anddroidCallToJSChangeBackgroundColor('"+color+"')";
        if(!activity.isFinishing()) {
            // loadurl on UI main thread
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl(webUrl);
                }
            });
        }
    }

    public void anddroidCallToJSChangeTextColor(String color) {
        final String webUrl = "javascript:anddroidCallToJSChangeTextColor('"+color+"')";
        if(!activity.isFinishing()) {
            // loadurl on UI main thread
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl(webUrl);
                }
            });
        }
    }

    /**
     * function shows Android-Native Alert Dialog
     */
    public void showDialog(String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

}
