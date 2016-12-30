package com.taiji.cc.scan.bean;

import java.io.Serializable;

/**
 * Created by panho on 2016/9/14.
 */
public class ScanResponse implements Serializable{
    /**
     * 扫描结果
     */
    private String result;
    /**
     * 结果类别
     */
    private int type;
    /**
     * 扫描状态码
     */
    private int status;
    /**
     * 扫描结果提示信息
     */
    private String message;
    /**
     * 病案号
     */
    private String pid;
    /**
     * 住院次数
     */
    private String vid;

    public ScanResponse() {
    }

    public ScanResponse(String result, int type, int status, String message, String pid, String vid) {
        this.result = result;
        this.type = type;
        this.status = status;
        this.message = message;
        this.pid = pid;
        this.vid = vid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    @Override
    public String toString() {
        return "ScanResponse{" +
                "result='" + result + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", pid='" + pid + '\'' +
                ", vid='" + vid + '\'' +
                '}';
    }
}
