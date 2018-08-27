package com.example.dh0023.tapping;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by gimminsu on 2018. 8. 27..
 */

public class ItemInfoView extends RelativeLayout {
    private Context mContext;
    private Button mBuyButton;
    private Button mItemButton;
    private WebView mWebView;
    private OnClickListener mListener;

    public interface OnClickListener {
        void onClick();
    }

    public ItemInfoView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ItemInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public ItemInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void setListener(OnClickListener listener) {
        mListener = listener;
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.item_info, this);

        mBuyButton = (Button) findViewById(R.id.btnBuy);
        mWebView = (WebView) findViewById(R.id.webview);
        mItemButton = (Button) findViewById(R.id.btnItem);

        //WebView init
        mWebView.loadUrl("http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("abcd", "onStarted : " + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d("abcd", "shouldOverrideUrlLoading : ");
                return true;
            }
        });

        // Button init
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mListener != null) {
//                    mListener.onClick();
//                }
                mWebView.setVisibility(View.VISIBLE);
                mBuyButton.setVisibility(View.GONE);
                mItemButton.setVisibility(View.GONE);
            }
        });

    }
}
