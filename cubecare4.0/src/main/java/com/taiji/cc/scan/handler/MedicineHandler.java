package com.taiji.cc.scan.handler;

import android.content.Context;

import com.taiji.cc.scan.bean.ScanRequest;

/**
 * Created by panho on 2016/9/14.
 */
public class MedicineHandler extends ScanHandler{

    public MedicineHandler(Context context) {
        super(context);
    }

    @Override
    protected boolean isType(ScanRequest request) {
        return false;
    }

    @Override
    protected void echo(ScanRequest request) {

    }
}
