package com.taiji.cc.scan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * 作者：panho on 2016-12-28 09:50
 * 邮箱: panhongzhi02@163.com
 * 功能描述：头扫描广播接收器
 */

public class ScanReceiver extends BroadcastReceiver{

    public static final String[] SCAN_FLAGS = new String[]{
            "com.motorolasolutions.emdk.datawedge.data_string",//摩托罗拉
            "lachesis_barcode_value_notice_broadcast_data_string",//新联
            "scanresult",//医惠
            "barcode_string"//清远
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        String scanResult = null;
        for (int i = 0; i < SCAN_FLAGS.length; i++) {
            scanResult = intent.getStringExtra(SCAN_FLAGS[i]);
            if(!TextUtils.isEmpty(scanResult)){
                break;
            }
        }

    }
}
