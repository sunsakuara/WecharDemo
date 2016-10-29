package zhuruyi.net.wechardemo.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import zhuruyi.net.wechardemo.R;

/**
 * Created by ruyi on 2016/10/24.
 * <p>
 * //正如前面代码看到的，在创建一个请求时，需要添加一个错误监听onErrorResponse。如果请求发生异常，会返回一个VolleyError实例。
 * //以下是Volley的异常列表：
 * //AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。
 * //NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。
 * //NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。
 * //ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
 * //SERVERERROR：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
 * //TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy
 */

public class VolleyErrorHelper {
    public static String getmessage(Context context, VolleyError error) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.server_error);
        } else if (isServerProblem(error)) {
            return HandleServerError(context, error);
        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_intents);
        }
        return context.getResources().getString(R.string.network_error);
    }

    /*
    * determine whether the error is related network*/
    private static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NetworkError)
                || (error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(VolleyError error) {
        return (error instanceof ServerError)
                || (error instanceof AuthFailureError);
    }

    private static String HandleServerError(Context context, VolleyError error) {
        NetworkResponse response=error.networkResponse;
        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:

                    return  context.getResources().getString(R.string.resourse_error);

                default:
                    return context.getResources().getString(
                            R.string.server_error);
            }
        }
        return context.getResources().getString(R.string.network_error);
    }

}
