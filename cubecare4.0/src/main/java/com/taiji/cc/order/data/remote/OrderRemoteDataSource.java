package com.taiji.cc.order.data.remote;

import android.content.Context;

import com.taiji.cc.order.data.OrderDataSource;
import com.taiji.cc.order.data.bean.OrderList;
import com.taiji.cc.order.data.bean.PageOrder;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.http.methods.HttpMethod;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-29 09:55
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */
public class OrderRemoteDataSource implements OrderDataSource {

    private static OrderRemoteDataSource INSTANCE;

    private Context mContext;

    public static OrderRemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRemoteDataSource.class) {
                INSTANCE = new OrderRemoteDataSource(context);
            }
        }
        return INSTANCE;
    }

    protected OrderRemoteDataSource(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<PageOrder> getOrders(String anam_id, String series, int start, int limit) throws Exception {
        OrderService service = HttpMethod.getInstance().getRetrofit(mContext).create(OrderService.class);
        return service.getPageOrders(anam_id,series,String.valueOf(start),String.valueOf(limit));
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
