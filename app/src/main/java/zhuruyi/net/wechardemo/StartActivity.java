package zhuruyi.net.wechardemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import zhuruyi.net.wechardemo.sys.Constant;

/**
 * Created by ruyi on 2016/10/25.
 */

public class StartActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //baidu推送SDK
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"qkplTLauzijpvx9sr4OGhD1s");

        mWebView = (WebView) findViewById(R.id.webview);
        //设置横向和纵向的滚动条
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWebView.loadUrl(Constant.API.URL_START);

        //跳到主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        }, 3000);
    }

    private void jump() {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        this.finish();
    }
}
