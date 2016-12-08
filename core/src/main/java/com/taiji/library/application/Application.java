package com.taiji.library.application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 描述：应用初始化类
 * 创建人： panho
 * 创建时间： 2016-11-28
 */

public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
