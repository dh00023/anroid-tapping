package com.example.dh0023.tapping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

public class OrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();

    }

    private void init(){
        CustomWebView webView = (CustomWebView) findViewById(R.id.orderView);
        // http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001
        // 바로구매 버튼으로 연결되는 url주소는 구매창이 제대로 뜨지 않고, 임의로 주소를 입력하면 뜨는데..!!
        // webView.loadUrl("https://base.cjmall.com/m/order/sheet/201808270122904665");
        Intent getIntent = getIntent();
        String landingUrl = getIntent.getStringExtra("url");
        if(!TextUtils.isEmpty(landingUrl)){
            webView.loadUrl(landingUrl);
        }
    }
}
