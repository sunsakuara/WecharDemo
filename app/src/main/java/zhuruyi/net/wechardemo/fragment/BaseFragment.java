package zhuruyi.net.wechardemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import zhuruyi.net.wechardemo.http.VolleyHttpClient;

/**
 * Created by ruyi on 2016/10/24.
 */

public class BaseFragment extends Fragment {
    protected VolleyHttpClient mFragmentHttpClient;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mFragmentHttpClient=VolleyHttpClient.getInstance(this);
    }
}
