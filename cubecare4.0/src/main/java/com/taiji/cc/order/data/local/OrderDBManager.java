package com.taiji.cc.order.data.local;

import android.content.Context;

import com.taiji.cc.greendao.db.BaseDBManager;
import com.taiji.cc.greendao.db.DaoSession;
import com.taiji.cc.greendao.db.OrderListDao;
import com.taiji.cc.order.data.bean.OrderList;
import com.taiji.cc.order.data.bean.PageOrder;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-29 09:49
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */
public class OrderDBManager extends BaseDBManager {

    private static OrderDBManager mInstance;

    public static OrderDBManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (OrderDBManager.class) {
                mInstance = new OrderDBManager(context);
            }
        }
        return mInstance;
    }

    protected OrderDBManager(Context context) {
        super(context);
    }

    public Observable<Void> insert(OrderList orderList){
        DaoSession daoSession = getWritableSession();
        OrderListDao dao = daoSession.getOrderListDao();
        RxDao rxDao = dao.rx();
        return rxDao.insert(orderList);
    }

    public Observable<Void> inserList(List<OrderList> orderLists){
        DaoSession daoSession = getWritableSession();
        OrderListDao dao = daoSession.getOrderListDao();
        RxDao rxDao = dao.rx();
        return rxDao.insertInTx(orderLists);
    }

    private List<OrderList> queryPage(String anam_id,String series,int start,int limit){
        DaoSession session = getReadableSession();
        OrderListDao dao = session.getOrderListDao();
        QueryBuilder<OrderList> queryBuilder = dao.queryBuilder();
        List<OrderList> orderLists = queryBuilder.orderDesc(OrderListDao.Properties.Dic_code).offset(start).limit(limit).list();
        return orderLists;
    }

    private int queryCount(String anam_id,String series){
        DaoSession session = getReadableSession();
        OrderListDao dao = session.getOrderListDao();
        QueryBuilder<OrderList> queryBuilder = dao.queryBuilder();
        return (int) queryBuilder.count();
    }

    public Observable<PageOrder> getPageOrder(String anam_id,String series,int start,int limit){
        PageOrder pageOrder = new PageOrder();
        pageOrder.setOrderLists(queryPage(anam_id, series, start, limit));
        pageOrder.setTotal(String.valueOf(queryCount(anam_id, series)));
        return Observable.just(pageOrder);
    }

    public Observable<OrderList> queryOne(String anam_id, String series, String dic_code){
        DaoSession session = getReadableSession();
        OrderListDao dao = session.getOrderListDao();
        QueryBuilder<OrderList> queryBuilder = dao.queryBuilder();
        RxQuery<OrderList> rxQuery = queryBuilder.where(OrderListDao.Properties.Anam_id.eq(anam_id),
                OrderListDao.Properties.Series.eq(series),
                OrderListDao.Properties.Dic_code.eq(dic_code)).rx();
        return rxQuery.unique();
    }

    public void deleteAll(){
        DaoSession session = getWritableSession();
        OrderListDao dao = session.getOrderListDao();
        dao.deleteAll();
    }

    public void deleteByPatient(String anam_id, String series){
        DaoSession session = getWritableSession();
        OrderListDao dao = session.getOrderListDao();
        QueryBuilder<OrderList> queryBuilder = dao.queryBuilder();
        DeleteQuery<OrderList> deleteQuery = queryBuilder.where(OrderListDao.Properties.Anam_id.eq(anam_id),
                OrderListDao.Properties.Series.eq(series)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

}
