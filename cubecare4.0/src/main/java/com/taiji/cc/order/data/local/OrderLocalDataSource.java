package com.taiji.cc.order.data.local;

import android.content.Context;

import com.taiji.cc.order.data.OrderDataSource;
import com.taiji.cc.order.data.bean.OrderList;
import com.taiji.cc.order.data.bean.PageOrder;
import com.taiji.cc.patient.data.local.PatientLocalDataSource;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.data.cache.CacheManager;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-28 10:14
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class OrderLocalDataSource implements OrderDataSource{

    private static OrderLocalDataSource INSTANCE;

    private Context mContext;

    private OrderDBManager mOrderDBManager;

    private OrderLocalDataSource(Context context){
        this.mContext = context;
        this.mOrderDBManager = OrderDBManager.getmInstance(context);
    }

    public static OrderLocalDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new OrderLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<PageOrder> getOrders(String anam_id, String series, int start, int limit) {
        return mOrderDBManager.getPageOrder(anam_id, series, start, limit);
    }

    @Override
    public Observable<OrderList> getOrder(String anam_id, String series, String dic_code) {
        return mOrderDBManager.queryOne(anam_id, series, dic_code);
    }

    @Override
    public void saveOrders(List<OrderList> orderLists) {
        mOrderDBManager.inserList(orderLists);
    }

    @Override
    public void saveOrder(OrderList orderList) {
        mOrderDBManager.insert(orderList);
    }

    @Override
    public void deleteAll() {
        mOrderDBManager.deleteAll();
    }

    @Override
    public void deleteByPatient(String anam_id, String series) {
        mOrderDBManager.deleteByPatient(anam_id, series);
    }

    @Override
    public void setCache(Cache cache) {
        Cache oldCache = CacheManager.getInstance(mContext).queryCache(cache.getCacheName());
        if(oldCache!=null){
            oldCache.setLastTime(cache.getLastTime());
            oldCache.setValidity(cache.getValidity());
        }else {
            oldCache = cache;
        }
        CacheManager.getInstance(mContext).insertReplace(oldCache);
    }
}
