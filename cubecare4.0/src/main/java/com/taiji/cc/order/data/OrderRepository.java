package com.taiji.cc.order.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.TimeUtils;
import com.taiji.cc.order.data.bean.Order;
import com.taiji.cc.order.data.bean.OrderList;
import com.taiji.cc.order.data.bean.PageOrder;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.data.cache.CacheManager;
import com.taiji.library.mvp.schedulers.SchedulerProvider;

import java.util.List;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 作者：panho on 2016-12-28 10:13
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class OrderRepository implements OrderDataSource{

    private static OrderRepository INSTANCE = null;

    private final OrderDataSource mOrderRemoteDataSource;

    private final OrderDataSource mOrderLocalDataSource;

    private Context mContext;

    private PageOrder mPageOrder;
    /**
     * 缓存是否有效
     */
    private boolean mCacheIsValid = false;

    public OrderRepository(Context context,
                           @NonNull OrderDataSource orderRemoteDataSource,
                           @NonNull OrderDataSource orderLocalDataSource){
        mContext = checkNotNull(context);
        mOrderRemoteDataSource = checkNotNull(orderRemoteDataSource,"orderRemoteDataSource不可为空");
        mOrderLocalDataSource = checkNotNull(orderLocalDataSource,"orderRemoteDataSource不可为空");
    }

    public static OrderRepository getInstance(Context context,
                                              @NonNull OrderDataSource orderRemoteDataSource,
                                              @NonNull OrderDataSource orderLocalDataSource){
        if(INSTANCE==null){
            INSTANCE = new OrderRepository(context,orderRemoteDataSource,orderLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }


    @Override
    public Observable<PageOrder> getOrders(String anam_id, String series, int start, int limit) throws Exception {
        mCacheIsValid = CacheManager.getInstance(mContext).isValid("order");
        if(mPageOrder!=null&&mCacheIsValid){
            return Observable.just(mPageOrder);
        }
        Observable<PageOrder> remoteOrder = getAndSaveRemoteOrder(anam_id,series,start,limit);
        if(!mCacheIsValid){
            return remoteOrder;
        }else {
            Observable<PageOrder> localOrder = getAndCacheLocalOrder(anam_id,series,start,limit);
            return Observable.concat(localOrder,remoteOrder).filter(new Func1<PageOrder, Boolean>() {
                @Override
                public Boolean call(PageOrder pageOrder) {
                    return pageOrder!=null;
                }
            }).first();
        }
    }

    private Observable<PageOrder> getAndCacheLocalOrder(String anam_id, String series, int start, int limit) throws Exception {
        return mOrderLocalDataSource.getOrders(anam_id, series, start, limit)
                .flatMap(new Func1<PageOrder, Observable<PageOrder>>() {
                    @Override
                    public Observable<PageOrder> call(PageOrder pageOrder) {
                        mPageOrder = pageOrder;
                        return Observable.just(mPageOrder);
                    }
                });
    }


    private Observable<PageOrder> getAndSaveRemoteOrder(final String anam_id, final String series, int start, int limit) throws Exception {
        return mOrderRemoteDataSource.getOrders(anam_id, series, start, limit)
                .flatMap(new Func1<PageOrder, Observable<PageOrder>>() {
                    @Override
                    public Observable<PageOrder> call(PageOrder pageOrder) {
                        deleteByPatient(anam_id,series);
                        mOrderLocalDataSource.saveOrders(pageOrder.getOrderLists());
                        mPageOrder = pageOrder;
                        mOrderLocalDataSource.setCache(new Cache(
                                null,
                                "order",
                                60,
                                TimeUtils.getCurTimeString()
                        ));
                        return Observable.just(pageOrder);
                    }
                })
                .subscribeOn(SchedulerProvider.getInstance().io())
                .unsubscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mCacheIsValid = true;
                    }
                });
    }

    @Override
    public Observable<OrderList> getOrder(String anam_id, String series, String dic_code) {
        return null;
    }

    @Override
    public void saveOrders(List<OrderList> orderLists) {

    }

    @Override
    public void saveOrder(OrderList orderList) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteByPatient(String anam_id, String series) {

    }

    @Override
    public void setCache(Cache cache) {

    }
}
