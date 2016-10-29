package zhuruyi.net.wechardemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import zhuruyi.net.wechardemo.R;

/**
 * Created by ruyi on 2016/10/13.
 * 自定义progress控件
 */

public class LoadingDialog {
    private Context mContext;
    private View mDialogView;
    private Dialog mDialog;
    private TextView mTetMsg;

    public LoadingDialog(Context context) {
        this.mContext = context;
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        mTetMsg = (TextView) mDialogView.findViewById(R.id.txt_msg);
        initDialog();
    }

    public void setMessage(CharSequence msg) {
        mTetMsg.setText(msg);
    }

    public void setMessage(int msg) {
        mTetMsg.setText(msg);
    }

    private void initDialog() {
        mDialog = new Dialog(mContext,R.style.dialog);
        mDialog.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(true);
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
    public boolean isShowing(){
        return mDialog.isShowing();
    }
}
