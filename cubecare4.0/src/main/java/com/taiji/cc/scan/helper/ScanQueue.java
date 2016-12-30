package com.taiji.cc.scan.helper;

import android.content.Context;

import com.taiji.cc.scan.bean.ScanCode;
import com.taiji.cc.scan.bean.ScanResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panho on 2016/9/19.
 * 扫描队列管理类
 */
public class ScanQueue {

    private OnFilterListener listener;

    private static final List<ScanResponse> responses = new ArrayList<>();

    private Context context;

    /**
     * 非占用队列数据类型数组集合（不需要和其他扫描类型对比）
     */
    public static final int[] nons = new int[]{ScanCode.MEDICINE_CODE};
    /**
     *
     */
    public static final int[] musts = new int[]{ScanCode.PATIENT_CODE};


    public ScanQueue(Context context){
        this.context = context;
    }

    public void addResponse(ScanResponse response,OnFilterListener listener){
        this.listener = listener;
        switch (response.getStatus()){
            case ScanCode.SCAN_SUCCESS:
                //只有扫描成功才进行队列过滤
                filter(response);
                break;
            default:
                //除了成功其他情况都属于失败
                if(listener!=null){
                    listener.onFaild(response);
                }
                break;
        }
    }

    /**
     * 过滤当前扫描结果，确定其在队列中的操作
     * @param response
     */
    private void filter(ScanResponse response){
        //首先判断队列长度
        if(responses.size()==0){
            //队列中没有任何扫描信息，直接添加扫描信息并调用成功回掉方法
            responses.add(response);
            if(listener!=null){
                listener.onSuccess(response);
            }
        }else if(responses.size()==1){
            //当前队列有1条扫描信息，判断新消息和已存在消息关系
            if(responses.get(0).getType()==response.getType()){
                responses.set(0,response);
                if(listener!=null){
                    listener.onSuccess(response);
                }
            }else {
                //包含占用队列类型的消息，直接处理为扫描成功
                if (isMust(responses.get(0).getType()) || isMust(response.getType())) {
                    //剩余2个都是占用队列类型
                    responses.add(response);
                    listener.onSuccess(response);
                    //进行匹配判断
                    if(isMatch()){
                        listener.onMatchSuccess(responses);
                    }else {
                        listener.onMatchFaild(responses);
                    }
                } else {
                    //不包含占用队列类型信息
                    responses.set(0, response);
                    if (listener != null) {
                        listener.onSuccess(response);
                    }
                }
            }
        }
    }

    /**
     * 判断消息类型是否为不占用扫描队列类型
     * @param type
     * @return true是占用类型 fals不是占用类型
     */
    private boolean isMust(int type){
        boolean flag = false;
        for (int i = 0; i < nons.length; i++) {
            if(type==musts[i]){
                flag = true;
            }
        }
        return flag;
    }

    private boolean isMatch(){
        boolean flag = false;
        if(responses.size()==2){
            if(responses.get(0).getPid().equals(responses.get(1).getPid())&&
                    responses.get(0).getVid().equals(responses.get(1).getVid())){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 扫描信息过滤成功回掉接口
     */
    public interface OnFilterListener{
        /**
         * 过滤成功
         * @param response
         */
        void onSuccess(ScanResponse response);

        /**
         * 过滤失败
         * @param response
         */
        void onFaild(ScanResponse response);

        /**
         * 匹配成功
         * @param responses
         */
        void onMatchSuccess(List<ScanResponse> responses);

        /**
         * 匹配失败
         * @param responses
         */
        void onMatchFaild(List<ScanResponse> responses);
    }
}
