package com.example.dh0023.tapping;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      WebView로 html 파일 연결하기
        final WebView webView = (WebView)findViewById(R.id.cjmall);

//      WebViewClient를 사용해 카메라 띄우기
        webView.setWebViewClient(new WebViewClient(){

            // 새로운 URL이 webview에 로드되려 할 경우 컨트롤을 대신할 기회를 줌
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("myTag", url);
                if (url.contains("camera")) {

                    //카메라 실행하는 코드
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);

                    return true;
                }

                return true;

            }
        });

        webView.loadUrl("file:///android_asset/index.html");
    }
}
