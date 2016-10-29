/*
*ChartFrament.java
*Created on 2014-8-12 下午3:00 by Ivan
*Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
*http://www.cniao5.com
*/
package zhuruyi.net.wechardemo.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import zhuruyi.net.wechardemo.R;
import zhuruyi.net.wechardemo.http.GsonRequest;
import zhuruyi.net.wechardemo.model.Weather;
import zhuruyi.net.wechardemo.widget.LoadingDialog;

/**
 * Created by Ivan on 14-8-12.
 * Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
 * http://www.cniao5.com
 * 聊天页面
 */
public class ChartFragment extends Fragment {
    public static final String TAG = "CharListFragment";
    private Button mButton;
    private LoadingDialog loadingDialog;
    //Volley
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());
        //设置消息队列
        mRequestQueue = Volley.newRequestQueue(getActivity());
    }

    //Volley中的字符串请求
    private StringRequest getStringRequest() {
        String URL = "https://www.baidu.com/";
        loadingDialog.setMessage("is requesting...");
        loadingDialog.show();
        //实例化一个StringRequest
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                loadingDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error stringRequeat");
                loadingDialog.dismiss();
            }
        })
                //为了适应post请求
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>(1);
                map.put("key", "value");
                return map;
            }
        };


        return stringRequest;
    }

    //Volley中的Json请求，还有JsonArrayRequst
    private JsonObjectRequest getJsonRequest() {
        loadingDialog.setMessage("is requesting...");
        loadingDialog.show();
        String URL = "http://ip.taobao.com/service/getIpInfo.php?ip=121.8.48.32";
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.d(TAG, response.toString());
                loadingDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                loadingDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>(1);
                map.put("key", "value");
                return map;
            }
        };
        return mJsonObjectRequest;
    }

    //Volley中的图像请求
    private ImageRequest getImageRequst() {
        loadingDialog.setMessage("is requesting...");
        loadingDialog.show();
        String URL = "http://pic34.nipic.com/20131003/3420027_161506084328_2.jpg";
        ImageRequest mImageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d(TAG, response.toString());
                loadingDialog.dismiss();
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ImageRequest  Error");
                loadingDialog.dismiss();
            }
        });
        return mImageRequest;
    }

    private GsonRequest getGsonRequest() {
        loadingDialog.dismiss();
        loadingDialog.setMessage("GsonRequest waiting...");
        String URL = "http://www.weather.com.cn/data/sk/101010100.html";
        GsonRequest<Weather> mGsonRequest = new GsonRequest<Weather>(URL, Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                Log.d(TAG, response.getWeatherInfo().getCity() + " temp:" + response.getWeatherInfo().getTemp());
                loadingDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "GsonRequest error");
                loadingDialog.dismiss();
            }
        });
        return mGsonRequest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_list, container, false);

        mButton = (Button) view.findViewById(R.id.showDialog);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });
        return view;
    }

    public void clickButton() {
        //把消息请求加入到消息队列当中
        mRequestQueue.add(getGsonRequest());
    }
}
