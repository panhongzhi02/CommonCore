package com.taiji.cc.scan.handler;

import android.content.Context;

import com.taiji.cc.exelist.data.remote.ExelistService;
import com.taiji.cc.scan.bean.ScanCode;
import com.taiji.cc.scan.bean.ScanRequest;
import com.taiji.cc.scan.bean.ScanResponse;
import com.taiji.library.http.methods.HttpMethod;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 输液条码处理类
 * Created by panho on 2016/9/14.
 */
public class InfusionHandler extends ScanHandler {

    public InfusionHandler(Context context) {
        super(context);
    }

    @Override
    protected boolean isType(ScanRequest request) {
        //对扫描到的内容进行判断，识别是否为输液瓶签
        return false;
    }

    @Override
    protected void echo(ScanRequest request) {
        //进行输液瓶签逻辑处理

        //从扫描信息中识别输液医嘱详细信息
        String[] parma = getInfusionParam(request);

    }

    @Override
    protected String[] getPatient(ScanRequest request) {
        return super.getPatient(request);
    }

    /**
     * 从扫描信息中获取输液医嘱详细信息
     * @param request
     * @return 【0】主医嘱号【1】执行日期【2】执行时间
     */
    private String[] getInfusionParam(ScanRequest request){
        return new String[3];
    }
    /**
     * 校验医嘱
     */
    private void checkOrder(String anam_id,String series,String dic_code,String date,String time){
        ExelistService service = HttpMethod.getInstance().getRetrofit("").create(ExelistService.class);
        service.checkOrder(anam_id,series,dic_code,date,time)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ScanResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ScanResponse response) {
                        switch (response.getStatus()){
                            case ScanCode.ORDER_NOT_EXIST_CODE:
                                //HIS系统中不存在该医嘱
                                break;
                            case ScanCode.ORDER_STOP_CODE:
                                //HIS系统中该医嘱已停止
                                break;
                            case ScanCode.ORDER_UNSYNC_CODE:
                                //移动护理数据库未同步到医嘱
                                break;
                            case ScanCode.NOT_ONE_CHECK_CODE:
                                //未进行一次核对
                                break;
                            case ScanCode.NOT_TWO_CHECK_CODE:
                                //未进行二次核对
                                break;
                            case ScanCode.CHECK_OUTDATE_CODE:
                                //医嘱不在有效的核对时间范围
                                break;
                            case ScanCode.EXECUTE_OUTDATE_CODE:
                                //医嘱不在有效的执行时间范围
                                break;
                        }
                    }
                });
    }


}
