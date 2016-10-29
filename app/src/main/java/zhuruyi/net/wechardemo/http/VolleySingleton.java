package zhuruyi.net.wechardemo.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ruyi on 2016/10/19.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequeatQueue;

    public static synchronized VolleySingleton getVolleySingleton(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    private VolleySingleton(Context context) {
        mRequeatQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getRequeatQueue() {
        return this.mRequeatQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequeatQueue().add(req);
    }

    public void cacleRequest(Object tag) {
        getRequeatQueue().cancelAll(tag);
    }
}
