package com.taiji.app.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.blankj.utilcode.utils.SDCardUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.SnackbarUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.taiji.app.R;
import com.taiji.library.ui.activity.BaseActivity;
import com.taiji.library.util.PermissionUtil;

import java.util.UUID;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-24
 */

public class UtilActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_layout);

        String time = TimeUtils.getCurTimeString();

        String path = SDCardUtils.getSDCardPath();

        boolean is = RegexUtils.isMobileSimple("18611900965");


    }
}
