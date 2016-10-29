package zhuruyi.net.wechardemo.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import zhuruyi.net.wechardemo.comment.util.ToastUtils;
import zhuruyi.net.wechardemo.widget.LoadingDialog;

/**
 * Created by ruyi on 2016/10/24.
 */

public class VolleyHttpClient {
    private VolleySingleton mVolleySingleton;
    private LoadingDialog mDialog;
    private Context mContext;
    /*单例设计模式*/
    private static VolleyHttpClient mInstance;

    //私有的构造防范
    private VolleyHttpClient(Context context) {
        mContext=context;
        mVolleySingleton = VolleySingleton.getVolleySingleton(context);
        mDialog = new LoadingDialog(context);

    }

    public static synchronized VolleyHttpClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHttpClient(context.getApplicationContext());
        }
        return mInstance;
    }
    public void post( String url, Map<String, String> params, int loadingMsg, final RequestListener listener){
        request(Request.Method.POST,url,params,loadingMsg,listener);
    }
    public void post( String url, int loadingMsg, final RequestListener listener){
        request(Request.Method.GET,url,null,loadingMsg,listener);
    }

    public void request(int method, String url, Map<String, String> params, int loadingMsg, final RequestListener listener) {
        if (listener != null) {
            listener.onPreRequest();
        }
        showLoading(loadingMsg);
        BaseRequest request = new BaseRequest(method, url, params, new Response.Listener<BaseRespone>() {
            @Override
            public void onResponse(BaseRespone response) {
                dissmissLoading();
                if (listener != null) {
                    if (response.isSucess()) {
                        listener.onRequestSuccess(response);
                    } else {
                        listener.onRequestFail(response.getStatus(), response.getMsg());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dissmissLoading();
                String errorMsg =VolleyErrorHelper.getmessage(mContext,error) ;

                ToastUtils.show(mContext,errorMsg);
                //do
                if (listener != null) {
                    listener.onRequestFail(error.networkResponse.statusCode, errorMsg);
                }
            }
        });
        mVolleySingleton.addToRequestQueue(request);
    }

    private void showLoading(int msg) {
        if (msg > 0) {
            mDialog.setMessage(msg);
            mDialog.show();
        }
    }

    private void dissmissLoading() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
