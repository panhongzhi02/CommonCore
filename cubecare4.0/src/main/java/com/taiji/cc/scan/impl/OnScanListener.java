package com.taiji.cc.scan.impl;

import com.taiji.cc.scan.bean.ScanResponse;

/**
 * Created by panho on 2016/9/19.
 * 扫描回掉接口
 */
public interface OnScanListener {

    void onEventMainThread(ScanResponse response);

}
