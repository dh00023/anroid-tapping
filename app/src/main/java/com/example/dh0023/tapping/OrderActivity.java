package com.example.dh0023.tapping;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order);

        WebView web = (WebView)findViewById(R.id.orderView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("order", url);
        // http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001
        // 바로구매 버튼으로 연결되는 url주소는 구매창이 제대로 뜨지 않고, 임의로 주소를 입력하면 뜨는데..!!
        web.loadUrl("https://base.cjmall.com/m/order/sheet/201808270122904665");
    }
}
