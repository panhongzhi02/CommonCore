package com.taiji.cc.scan.handler;

import android.content.Context;

import com.taiji.cc.patient.data.Patient;
import com.taiji.cc.patient.data.local.PatientDBManager;
import com.taiji.cc.scan.bean.ScanCode;
import com.taiji.cc.scan.bean.ScanRequest;
import com.taiji.cc.scan.bean.ScanResponse;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by panho on 2016/9/14.
 */
public abstract class ScanHandler {

    protected Context context;

    protected String pid;

    protected String vid;
    /**
     * 经过处理的扫描信息
     */
    protected ScanResponse response;

    private ScanHandler(){

    }

    protected ScanHandler(Context context){
        this.context = context;
        response = new ScanResponse();
    }

    /**
     * 下一个处理类
     */
    private ScanHandler nextHandler;

    public final void doHandler(ScanRequest request){
        //判断是否应该由自己来处理
        if(isType(request)){
            doEcho(request);
        }else {
            if(nextHandler!=null){
                //交给下一个处理类
                nextHandler.doHandler(request);
            }else {
                //没有下一个处理类
                ScanResponse response = new ScanResponse();
                response.setType(ScanCode.UNABLE_CODE);
                response.setResult(request.getContent());
                response.setStatus(ScanCode.UNABLE_TO_IDENTIFY_CODE);
                response.setMessage(ScanCode.UNABLE_TO_IDENTIFY_MESSAGE);
                postMessage(response);
            }
        }
    }

    public void setNextHandler(ScanHandler handler){
        this.nextHandler = handler;
    }

    /**
     * 判断病区是否存在该患者
     * @return
     */
    protected boolean isPatientExist(){
        final boolean[] isExist = {false};
        if(pid==null||vid==null) {
            return isExist[0];
        }
        Observable<Patient> patient = PatientDBManager.getmInstance(context).queryPatient(pid,vid);
        patient.doOnNext(new Action1<Patient>() {
            @Override
            public void call(Patient patient) {
                if(patient!=null){
                    isExist[0] = true;
                }
            }
        });
        return isExist[0];
    }

    /**
     * 构建患者不存在的扫描结果信息
     * @param request
     * @return 返回结果中不包含扫描类别
     */
    protected ScanResponse builderPatientNotExistResponse(ScanRequest request){
        if(response==null){
            response = new ScanResponse();
        }
        response.setStatus(ScanCode.PATIENT_NOT_EXIST_CODE);
        response.setResult(request.getContent());
        response.setMessage(ScanCode.PATIENT_NOT_EXIST_MESSAGE);
        return response;
    }

    /**
     * 扫描消息通讯注册
     */
    public static void scanRegister(Context context){
        EventBus.getDefault().register(context);
    }

    /**
     * 扫描消息通讯注销
     */
    public static void scanDestory(Context context){
        EventBus.getDefault().unregister(context);
    }

    /**
     * 将扫描到并经过处理的信息发送到各个界面
     * @param response
     */
    public void postMessage(ScanResponse response){
        EventBus.getDefault().post(response);
    }

    /**
     * 判断是否处理该请求
     * @param request
     * @return
     */
    protected abstract boolean isType(ScanRequest request);

    /**
     * 父类中处理一些共公信息
     */
    private void doEcho(ScanRequest request){
        pid = getPatient(request)[0];
        vid = getPatient(request)[1];
        if(isPatientExist()){
            //切换当前患者
//            CommonFactory.getFactory(context).changePatient(pid,vid);
            response.setPid(pid);
            response.setVid(vid);
            response.setType(ScanCode.PATIENT_CODE);
            response.setStatus(ScanCode.SCAN_SUCCESS);
            response.setResult(request.getContent());
            response.setMessage(ScanCode.SCAN_PATIENT_SUCCESS_MESSAGE);
            echo(request);
        }else {
            response = builderPatientNotExistResponse(request);
            response.setType(ScanCode.PATIENT_CODE);
        }
        postMessage(response);
    }
    /**
     * 交给子类实现对扫描请求的具体处理
     * @param request
     */
    protected abstract void echo(ScanRequest request);

    /**
     * 识别扫描信息中的病案号和住院次数
     * @param request
     * @return 0为病案号 1为住院次数
     */
    protected String[] getPatient(ScanRequest request){

        return new String[1];
    }

}
