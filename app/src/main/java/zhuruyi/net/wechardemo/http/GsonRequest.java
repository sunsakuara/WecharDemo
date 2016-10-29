package zhuruyi.net.wechardemo.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by ruyi on 2016/10/14.
 * 自定义Request，现在还有些问题
 */

public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Class<T> mClass;
    private Gson mGson;
    private Map<String, String> mParams;

    public GsonRequest(int method, String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener, Map<String, String> params) {
        super(method, url, errorListener);
        this.mClass = mClass;
        this.mListener = listener;
        mGson = new Gson();
        this.mParams = params;
    }

    public GsonRequest(String url, Class<T> mClass, Response.Listener mListener, Response.ErrorListener mErrorListener) {
        this(Method.POST, url, mClass, mListener, mErrorListener, null);
    }


    /*解析请求*/

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
    /*发送请求*/

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public int compareTo(Request<T> other) {
        return super.compareTo(other);
    }

    //为了解决BasicNetwork.performRequest: Unexpected response code 405 for ；这个问题

}
