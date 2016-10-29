package zhuruyi.net.wechardemo;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/**
 * Created by ruyi on 2016/10/27.
 */

public class pushReceiver extends PushMessageReceiver {

    public static final String TAG = "pushReceiver";

    @Override
    public void onBind(Context context, int errorCode, String appId, String userId, String channleId, String requestId) {
        Log.d(TAG, "errorCode=" + errorCode);
        Log.d(TAG, "appId=" + appId);
        Log.d(TAG, "userId=" + userId);
        Log.d(TAG, "channleId=" + channleId);
        Log.d(TAG, "requestId=" + requestId);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String s, String s1) {

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
