package com.taiji.library.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 描述：TabLayout内的自定义控件基类
 * 创建人： panho
 * 创建时间： 2016-11-18
 */

public abstract class BasePageView extends LinearLayout{

    protected Context mContext;

    public BasePageView(Context context) {
        this(context,null);
    }

    public BasePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    /**
     * 传递参数
     * @param param
     */
    public abstract void onRefreshData(String...param);

}
