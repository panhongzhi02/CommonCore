package com.taiji.cc.order.data.remote;

import com.taiji.cc.order.data.bean.PageOrder;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：panho on 2016-12-28 10:17
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public interface OrderService {

    /**
     * 获取医嘱列表
     * @param anam_id 病案号
     * @param series 住院次数
     * @param start 起始行
     * @param limit 每页行数
     * @return
     */
    @GET("order/selectPageOrders")
    Observable<PageOrder> getPageOrders(
            @Query("anam_id")String anam_id,
            @Query("series")String series,
            @Query("start")String start,
            @Query("limit")String limit
    );

}
