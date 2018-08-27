package com.example.dh0023.tapping;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_camera);
    }

    public void onBtnClicked(View view){
        container = (RelativeLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_info,container,true);
        final Button button = (Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
    }

    public void buyBtnClicked(View view){

        container = (RelativeLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.buy,container,true);

        WebView web = (WebView)findViewById(R.id.buyView);


        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        web.setWebViewClient(new WebViewClient());
        web.setWebViewClient(new WebViewClient(){

            // 바로구매 버튼을 클릭하면 새로운 Activity로 연결되도록!
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("myTag", url);
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);


                return true;

            }
        });

        web.loadUrl("http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001");


    }
}
