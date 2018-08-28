package com.example.dh0023.tapping;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private CustomWebView mWebView;
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

    /**
     * initView
     */
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.item_info, this);

        mBuyButton = (Button) findViewById(R.id.btnBuy);
        mWebView = (CustomWebView) findViewById(R.id.webview);
        mItemButton = (Button) findViewById(R.id.btnItem);
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.setVisibility(View.VISIBLE);
                mBuyButton.setVisibility(View.GONE);
                mItemButton.setVisibility(View.GONE);
            }
        });

        //WebView load
        initWebView();
        mWebView.loadUrl("http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=50001002");
    }

    /**
     * webview init
     */
    private void initWebView(){
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("url", url);
                mContext.startActivity(intent);
                return true;
            }
        });
    }
}
