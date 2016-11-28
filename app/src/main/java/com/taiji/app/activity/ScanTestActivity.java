package com.taiji.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.taiji.app.R;
import com.taiji.library.ui.activity.ScanActivity;
import com.taiji.library.util.L;

/**
 * 描述：扫描二维码测试
 * 创建人： panho
 * 创建时间： 2016-11-28
 */

public class ScanTestActivity extends ScanActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_test_acy);
        setTitle("二维码扫描");
    }

    @Override
    protected void onScanResult(String result) {
        L.d(result);
    }
}
