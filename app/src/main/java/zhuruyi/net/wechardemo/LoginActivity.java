package zhuruyi.net.wechardemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import zhuruyi.net.wechardemo.widget.ClearEditText;

/**
 * Created by ruyi on 2016/10/29.
 */

public class LoginActivity extends BaseActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ClearEditText mClearEditText=new ClearEditText(this);
        mClearEditText.setShakeAnimation();
    }
}
