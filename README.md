# Tap-ping 개발

### 180824

1. CJmall 마크업 해오기
2. Android Studio WebView 이용해서 html파일 불러오기

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">


    <WebView
        android:id="@+id/cjmall"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

```java
package com.example.dh0023.tapping;

import android.support.v7.app인AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.cjmall);
        webView.loadUrl("file:///android_asset/index.html");
    }
}
```

manifiest에 인터넷 권한 설정해주기

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.dh0023.tapping">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
</manifest>
```

3. WebViewClient의 shouldOverrideUrlLoading 함수를 사용해 페이지 이동막기
	- [참조페이지](http://cofs.tistory.com/186)

4. 새로운 Activity 생성후  shouldOverrideUrlLoading에서 연결하기
5. 상품 정보 뜨는 레이아웃 만들기
	- [참조페이지](http://wimir-dev.tistory.com/11)
6. 김민수님 - 카메라 연결 부분 전체적으로 수정해주심
7. 세부사항 수정





- [Display image from url](https://stackoverflow.com/questions/3118691/android-make-an-image-at-a-url-equal-to-imageviews-image)
- [imageView Devise 오류](https://stackoverflow.com/questions/30844395/image-showing-in-android-studio-but-not-on-phone/40487304)
    - 이미지크기때문이었다.


### 상품정보

- [스톤헨지 귀걸이](http://display.cjmall.com/p/item/49595372?channelCode=30001001&k=%EC%8A%A4%ED%86%A4%ED%97%A8%EC%A7%80)


```xml
<ImageView
        android:id="@+id/item_image_first"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:layout_marginLeft="100dp"
        android:layout_marginTop="100dp"
        android:background="@drawable/transparent_border"
        android:visibility="gone" />

    <ImageView
        android:id="@+id/item_image_second"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:layout_marginRight="150dp"
        android:layout_marginTop="200dp"
        android:background="@drawable/transparent_border"
        android:visibility="gone" />
```