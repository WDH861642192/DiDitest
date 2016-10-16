package com.example.wangdonghai;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Checkable;

import com.example.wangdonghai.didiactivity.R;

/**
 * Created by wangdonghai on 16/10/16.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private ImageView CheckImage;
    private boolean mCheck=false;
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        mCheck=!mCheck;
        CheckImage.setImageResource(mCheck?R.mipmap.is_check:R.mipmap.no_check);
    }

    @Override
    public boolean isChecked() {
        return mCheck;
    }

    @Override
    public void toggle() {

    }
    public void ImageView(ImageView imageView){
        CheckImage=imageView;
    }
}
