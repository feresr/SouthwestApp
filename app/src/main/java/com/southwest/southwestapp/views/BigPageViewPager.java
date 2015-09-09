package com.southwest.southwestapp.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by luisalfonsobejaranosanchez on 9/9/15.
 */
public class BigPageViewPager extends ViewPager {

    private boolean enabled;

    public BigPageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
        this.setOffscreenPageLimit(2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPagingEnabled() {
        return this.enabled;
    }

}
