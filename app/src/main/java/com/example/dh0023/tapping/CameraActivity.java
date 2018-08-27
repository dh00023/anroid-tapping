package com.example.dh0023.tapping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CameraActivity extends AppCompatActivity {
    public RelativeLayout container;
    public Button mBuyButton;
    public WebView mWebView;
    public ItemInfoView mItemInfoView;
    public SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        container = (RelativeLayout) findViewById(R.id.container);
        mItemInfoView = (ItemInfoView) findViewById(R.id.iteminfo_view);
        mSurfaceView = (SurfaceView) findViewById(R.id.camera_view);
        init();
    }

    private void init() {

    }

    public void onBtnClicked(View view) {
        final Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.GONE);

        mItemInfoView.setVisibility(View.VISIBLE);
    }
}
