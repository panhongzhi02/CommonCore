package com.taiji.cc.scan.bean;

/**
 * 扫描请求实体
 * Created by panho on 2016/9/14.
 */
public class ScanRequest {
    /**
     * 请求内容
     */
    private String content;

    public ScanRequest(){
        super();
    }

    public ScanRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ScanRequest{" +
                ", content='" + content + '\'' +
                '}';
    }
}
