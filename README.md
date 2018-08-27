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


**질문하기**
layoutinflater를 이용해서 버튼을 클릭하면 상품정보레이어가 나타나도록 구현했다. 그런데 상품정보레이어에 있는 button의 event처리가 안된다.
그래서 우선적으로 CameraActivity에서 구매창이 뜨도록 하는 버튼 이벤트 처리를 따로 구현해보았다.
`webView.loadUrl("http://display.cjmall.com/m/item/49595372/itemOrderOption?isNeededInterface=true&channelCode=30001001");`

1. 웹뷰를 그냥 이미지와 상품정보 위에 얹었는데 아래의 글과 이미지가 사라져요.. 이걸 어떻게 해야하나요? 새로운 레이아웃을 생성해서 그게 연결되도록 해야하나요?
2. 의문점! url로 바로구매버튼을 눌렀을 경우 그 웹뷰 내부에서 바로 cjmall바로구매페이지로 이동하게 되는데 그럼 이건 `shouldOverrideUrlLoading`에서 새로운 Activity로 연결해서 뜨게 하는건가요?




- [Display image from url](https://stackoverflow.com/questions/3118691/android-make-an-image-at-a-url-equal-to-imageviews-image)




### 상품정보

- [스톤헨지 귀걸이](http://display.cjmall.com/p/item/49595372?channelCode=30001001&k=%EC%8A%A4%ED%86%A4%ED%97%A8%EC%A7%80)


