package com.example.dh0023.tapping;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final int RESULT_PERMISSIONS = 888;
    private RelativeLayout container;
    private Button mBuyButton;
    private ImageButton mItemButton;
    private ImageButton mItemButton2;
    private WebView mWebView;
    private ItemInfoView mItemInfoView;
    private SurfaceView mCameraView;
    private SurfaceHolder mCameraHolder;
    private Camera mCamera;
    private ImageView mLogoLeftImage;
    private ImageView mLogoRightImage;
//    private ImageView mFirstItemImage;
//    private ImageView mSecondItemImage;
    private ImageView mPreItemImage;
    private Subscription mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);

        container = (RelativeLayout) findViewById(R.id.container);
        mItemInfoView = (ItemInfoView) findViewById(R.id.iteminfo_view);
        mCameraView = (SurfaceView) findViewById(R.id.camera_view);

        mLogoLeftImage = (ImageView) findViewById(R.id.logo_image_left);
        mLogoRightImage = (ImageView) findViewById(R.id.logo_image_right);
//        mFirstItemImage = (ImageView) findViewById(R.id.item_image_first);
//        mSecondItemImage = (ImageView) findViewById(R.id.item_image_second);

        mPreItemImage = (ImageView) findViewById(R.id.mPreItemImage);
        mItemButton = (ImageButton) findViewById(R.id.tapBtn);
        mItemButton2 = (ImageButton) findViewById(R.id.tapBtn2);

        requestPermissionCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.unsubscribe();
            mTimer = null;
        }
    }

    /**
     * 버튼 별 interval 시작
     */
    private void startInterval() {
        if (mTimer != null) {
            return;
        }
        mTimer = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .onBackpressureDrop()
                .onErrorReturn(new Func1<Throwable, Long>() {
                    @Override
                    public Long call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long currentSecond) {
                        switch (currentSecond.intValue()) {
                            case 2:
                                mLogoLeftImage.setVisibility(View.VISIBLE);
                                mLogoRightImage.setVisibility(View.VISIBLE);
                                break;

                            case 3:
//                                mFirstItemImage.setVisibility(View.VISIBLE);
                                mLogoLeftImage.setVisibility(View.GONE);
                                mLogoRightImage.setVisibility(View.GONE);
                                mItemButton.setVisibility(View.VISIBLE);
                                mItemButton2.setVisibility(View.VISIBLE);
                                mPreItemImage.setVisibility(View.VISIBLE);

                                break;
//
//                            case 6:
//                                mSecondItemImage.setVisibility(View.VISIBLE);
//                                break;

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    /**
     * camera open
     */
    private void initCamera() {
        if (mCamera == null) {
            mCamera = Camera.open();
            mCameraHolder = mCameraView.getHolder();
            mCameraHolder.addCallback(this);
        }
    }

    /**
     * onclicklistener
     * @param view
     */
    public void onBtnClicked(View view) {

        mItemButton.setVisibility(View.GONE);
        mItemButton2.setVisibility(View.GONE);

        mItemInfoView.setVisibility(View.VISIBLE);
    }

    /**
     * camera 퍼미션 요청
     */
    private void requestPermissionCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RESULT_PERMISSIONS);
        } else {
            initCamera();
        }
    }

    /**
     * camera 퍼미션 요청 (거부 시 종료)
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (RESULT_PERMISSIONS == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initCamera();
            } else {
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /**
     * 카메라뷰 시작
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            initCamera();
            if (mCamera != null) {
                startInterval();

                mCamera.setPreviewDisplay(surfaceHolder);
                Camera.Parameters parameters = mCamera.getParameters();

                // activity orientaion landscape(가로모드)로 강제로 고정했기 때문에 별도 분기처리 X
                parameters.set("orientation", "landscape");
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);

                // focus
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                mCamera.setParameters(parameters);
                mCamera.startPreview();
            }
        } catch (Exception e) {
            Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 카메라 뷰 변경
     * @param surfaceHolder
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            mCamera.stopPreview();
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 카메라뷰 destroy
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


}
