package com.taiji.library.ui.helper;

import android.content.Context;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-18
 */

public class PageHelper {

    private int total;//总行数

    private int page_size;//每页行数

    private int mCurrentCounter = 0;//当前行数

    private Context mContext;

    private OnPageChangeListener mListener;

    public PageHelper(Context context,int page_size){
        this.page_size = page_size;
        this.mContext = context;
    }

    /**
     * 初始化分页组件
     */
    public void init(){
        mCurrentCounter = 0;
        if(mListener!=null){
            mListener.onInit();
        }
    }

    /**
     * 每次数据加载完成是调用
     * @param total 总行数
     */
    public void loadComplate(int total){
        if(total>0){
            this.total = total;
            if(mCurrentCounter==0){
                if(mListener!=null){
                    mListener.onRefresh();
                }
            }
        }
    }

    /**
     * 分页加载时调用
     */
    public void pageLoad(){
        if(mCurrentCounter>=total){
            if(mListener!=null){
                mListener.onAllComplete();
            }
        }else {
            if(mListener!=null){
                mListener.loadMore();
            }
        }
    }

    /**
     * 设置列表当前行数
     * @param currentCounter
     */
    public void setCurrentCounter(int currentCounter){
        mCurrentCounter = currentCounter;
    }

    public void setOnPageChangeListener(OnPageChangeListener listener){
        this.mListener = listener;
    }

    public interface OnPageChangeListener{
        /**
         * 分页初始化  (初始化时调用,下拉刷新时调用,移除底部控件)
         */
        void onInit();
        /**
         * 刷新数据（下拉刷新时回调）
         */
        void onRefresh();
        /**
         * 加载更多（滑动加载更多时回调）
         */
        void loadMore();
        /**
         * 加载完成（全部数据加载完成回调）
         */
        void onAllComplete();

    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getStart(){
        return mCurrentCounter;
    }

    public int getEnd(){
        return mCurrentCounter+page_size;
    }
}
