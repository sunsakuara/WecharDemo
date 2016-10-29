package zhuruyi.net.wechardemo.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import zhuruyi.net.wechardemo.R;

/**
 * Created by ruyi on 2016/10/10.
 */

public class tabUtils {
    public static View readerTabView(Context context, int titleResource, int badgeNumber) {
        FrameLayout view = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.tab_badge, null);
        view.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((TextView)view.findViewById(R.id.tab_text)).setText(titleResource);
        updateTabBadge((TextView) view.findViewById(R.id.tab_badge),badgeNumber);
        return view;
    }
    public static void updateTabBadge(RadioButton radioButton,int badgeNumber){
        updateTabBadge((TextView) radioButton.getRootView().findViewById(R.id.tab_badge),badgeNumber);
    }
    private static void updateTabBadge(TextView view,int badgeNumber){
        if (badgeNumber>0){
            view.setVisibility(View.VISIBLE);
            view.setText(Integer.toString(badgeNumber));
        }else{
            view.setVisibility(View.GONE);
        }
    }
}
