package zhuruyi.net.wechardemo.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ruyi on 2016/10/23.
 */

public class BaseRequest extends Request<BaseRespone> {

    private  Response.Listener<BaseRespone> mListener;
    private Map<String,String> mParams;

    public BaseRequest(int method, String url, Map<String,String> params, Response.Listener listener,Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener=listener;
        this.mParams=params;
    }

    @Override
    protected Response<BaseRespone> parseNetworkResponse(NetworkResponse response) {

        String jsonString = null;
        try {
            jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            BaseRespone baseresponse = parseJson(jsonString);
            return Response.success(baseresponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return super.getParams();
    }

    @Override
    protected void deliverResponse(BaseRespone response) {
              mListener.onResponse(response);
    }

    private BaseRespone parseJson(String json) {
        int status = 0;
        String msg = null;
        String data = null;
        try {
            JSONObject jsonObject = new JSONObject();
            status = jsonObject.getInt("status");
            msg = jsonObject.getString("msg");
            data = jsonObject.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BaseRespone respone = new BaseRespone();

        respone.setStatus(status);
        respone.setMsg(msg);
        respone.setData(data);

        return respone;
    }
}
