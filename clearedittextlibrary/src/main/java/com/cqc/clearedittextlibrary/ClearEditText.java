package com.cqc.clearedittextlibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ${cqc} on 2017/8/29.
 */

public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    /**
     * 删除按钮的引用
     */
    Drawable drawable2;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        drawable2 = getCompoundDrawables()[2];
        if (drawable2 == null) {
            drawable2 = getResources().getDrawable(R.drawable.edittext_delete);
        }

        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        setPadding(10, getPaddingTop(), 10, getPaddingBottom());//注释掉也没事
        //默认设置隐藏图标
        setClearVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableRight = drawables[2];

        if (event.getAction() == MotionEvent.ACTION_UP && drawableRight != null) {
            if (event.getX() > (getWidth() - getPaddingRight() - drawableRight.getIntrinsicWidth())) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (getText().length() > 0) {
                setClearVisible(true);
            } else {
                setClearVisible(false);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            setClearVisible(true);
        } else {
            setClearVisible(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    public void setClearVisible(boolean visible) {
        Drawable drawableRight = visible ? drawable2 : null;
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], drawableRight, drawables[3]);
    }
}
