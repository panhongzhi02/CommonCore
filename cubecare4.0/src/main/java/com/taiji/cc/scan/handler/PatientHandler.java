package com.taiji.cc.scan.handler;

import android.content.Context;

import com.taiji.cc.scan.bean.ScanRequest;


/**
 * 患者腕带码处理类
 * Created by panho on 2016/9/14.
 */
public class PatientHandler extends ScanHandler {

    public PatientHandler(Context context) {
        super(context);
    }

    @Override
    protected boolean isType(ScanRequest request) {
        //对扫描到的内容进行判断，识别是否为腕带
        return false;
    }

    @Override
    protected void echo(ScanRequest request) {
        //扫描到的是腕带，并且患者在病区
    }

    @Override
    protected String[] getPatient(ScanRequest request) {
        return new String[1];
    }
}
