package zhuruyi.net.wechardemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import zhuruyi.net.wechardemo.R;

/**
 * Created by ruyi on 2016/10/12.
 */

public class CnToobar extends Toolbar {

    private LayoutInflater mInflater;
    private EditText mSearchView;
    private TextView mTextTitle;
    private ImageButton mRightImageButton;
    private View mView;

    public CnToobar(Context context) {
        this(context, null);
    }

    public CnToobar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CnToobar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentInsetsRelative(10, 10);
        initview();

        if (attrs != null) {
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CnToobar, defStyleAttr, 0);
            final Drawable rightIcon = a.getDrawable(R.styleable.CnToobar_rightButtonIcon);
            if (rightIcon != null) {
                // setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }
            boolean isSearchView = a.getBoolean(R.styleable.CnToobar_isShowSearchView, false);
            if (isSearchView) {
                showSearchView();
                hideTitleView();
            }
            a.recycle();
        }
    }

    private void initview() {
        if (mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar, null);

            mSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mRightImageButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);

            LayoutParams mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

            addView(mView, mLayoutParams);
        }
    }

    public void setRightButtonIcon(Drawable icon) {
        if (mRightImageButton != null) {
            mRightImageButton.setImageDrawable(icon);
            mRightImageButton.setVisibility(VISIBLE);
        }
    }

    public void setRightButtonOnClickedListener(OnClickListener listener) {
        mRightImageButton.setOnClickListener(listener);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initview();
        if (mTextTitle != null) {
            mTextTitle.setText(title);
            showTitlleView();
        }
    }

    public void showTitlleView() {
        if (mTextTitle != null) {
            mTextTitle.setVisibility(VISIBLE);
        }
    }

    public void hideTitleView() {
        if (mTextTitle != null) {
            mTextTitle.setVisibility(GONE);
        }
    }

    public void showSearchView() {
        if (mSearchView != null) {
            mSearchView.setVisibility(VISIBLE);
        }
    }

    public void hideSearchView() {
        if (mSearchView == null) {
            mSearchView.setVisibility(GONE);
        }
    }
}
