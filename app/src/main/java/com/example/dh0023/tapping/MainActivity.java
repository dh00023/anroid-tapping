package com.example.dh0023.tapping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.cjmall);
        webView.loadUrl("file:///android_asset/index.html");
//        file:///Users/dh0023/Desktop/tapping/index.html
    }
}
