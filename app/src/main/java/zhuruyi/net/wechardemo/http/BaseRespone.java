package zhuruyi.net.wechardemo.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruyi on 2016/10/23.
 */

public class BaseRespone {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm;ss").create();
    public static  final int SUCCESS=1;
    public static  final int FAIL=0;

    int status = 0;
    String msg = null;
    String data = null;


    public <T> T getObjext(Class<T> clazz) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }

        return gson.fromJson(data, clazz);
    }

    public <T> List<T> getList(Class<T> clazz) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        Type listType = type(List.class, clazz);
        list = gson.fromJson(data, listType);
        return list;
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSucess(){
        return status==SUCCESS;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
