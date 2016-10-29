package zhuruyi.net.wechardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhuruyi.net.wechardemo.http.VolleyHttpClient;

/**
 * Created by ruyi on 2016/10/24.
 */

public class BaseActivity extends AppCompatActivity {
    protected VolleyHttpClient mHttpVlient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHttpVlient=VolleyHttpClient.getInstance(this);
    }
}
