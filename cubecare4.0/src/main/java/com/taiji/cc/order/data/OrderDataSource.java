package com.taiji.cc.order.data;

import com.taiji.cc.order.data.bean.Order;
import com.taiji.cc.order.data.bean.OrderList;
import com.taiji.cc.order.data.bean.PageOrder;
import com.taiji.library.data.cache.Cache;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-28 10:13
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public interface OrderDataSource {
    /**
     * 获取某一患者医嘱列表
     * @param anam_id 病案号
     * @param series 住院次数
     * @param start 起始行
     * @param limit 每页行数
     * @return
     */
    Observable<PageOrder> getOrders(String anam_id, String series, int start, int limit) throws Exception;

    /**
     * 获取某一患者某一主医嘱列表
     * @param anam_id
     * @param series
     * @param dic_code
     * @return
     */
    Observable<OrderList> getOrder(String anam_id, String series, String dic_code);

    /**
     * 保存医嘱列表
     * @param orderLists
     */
    void saveOrders(List<OrderList> orderLists);

    /**
     * 保存单条医嘱
     * @param orderList
     */
    void saveOrder(OrderList orderList);

    /**
     * 删除整张医嘱表
     */
    void deleteAll();

    /**
     * 删除患者医嘱信息
     * @param anam_id
     * @param series
     */
    void deleteByPatient(String anam_id,String series);

    /**
     * 设置缓存
     * @param cache
     */
    void setCache(Cache cache);

}
