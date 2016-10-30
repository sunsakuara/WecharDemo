package zhuruyi.net.wechardemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import zhuruyi.net.wechardemo.R;

/**
 * Created by ruyi on 2016/10/29.
 */

public class ClearEditText extends EditText implements OnFocusChangeListener, TextWatcher {
    /*
    * 删除按钮的引用*/
    private Drawable mClearDrawable;
    /*
    * 控件是否有焦点*/
    private boolean hasFouce;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.editText);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.editText);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        //获取EditText的DrawableRight，假设没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            //throw a new NullPointException("You can add a drawableRight attributr in XML");
            mClearDrawable = getResources().getDrawable(R.drawable.selector_ic_delete, null);
        }
        //getIntrinsicWidth()取得的是Drawable在手机上的宽度，所以不同分辨率下的值是不同的，关键所在处
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());

        //默认设置隐藏图标
        setClearIconVisiable(true);
        //设置焦点改变监听
        setOnFocusChangeListener(this);
        //设置输入框改变时的监听
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                //是否点击在图标的坐标上
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /*设置清楚图标的显示与隐藏，调用setCompoundDrawables();为EditText绘制上去
        * @param Visible*/
    protected void setClearIconVisiable(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFouce = hasFocus;
        if (hasFocus) {
            setClearIconVisiable(getText().length() > 0);
        } else {
            setClearIconVisiable(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (hasFouce) {
            setClearIconVisiable(text.length() > 0);
        }
    }

    /*  设置晃动动画*/
    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }

    /*
    * 晃动动画，
    * @param counts 一秒晃动多少下
    * @return*/

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
