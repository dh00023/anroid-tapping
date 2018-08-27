package com.example.dh0023.tapping;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CameraActivity extends AppCompatActivity {
    RelativeLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    public void onBtnClicked(View view){
        container = (RelativeLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.product_info,container,true);
        final Button button = (Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);


    }

    public void buyBtnClicked(View view){
        WebView web = (WebView)findViewById(R.id.buyView);
        web.setWebViewClient(new WebViewClient());

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);


        web.loadUrl("http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001");
        final Button button1 = (Button)findViewById(R.id.btnBuy);
        button1.setVisibility(View.INVISIBLE);

        final Button button2 = (Button)findViewById(R.id.btnItem);
        button2.setVisibility(View.INVISIBLE);
    }
}
