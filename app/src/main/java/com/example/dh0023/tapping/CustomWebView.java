package com.example.dh0023.tapping;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by gimminsu on 2018. 8. 28..
 */

public class CustomWebView extends WebView {
    private Context mContext;
    public CustomWebView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
