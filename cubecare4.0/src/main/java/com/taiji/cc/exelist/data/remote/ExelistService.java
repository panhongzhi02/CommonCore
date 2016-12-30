package com.taiji.cc.exelist.data.remote;

import com.taiji.cc.scan.bean.ScanResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：panho on 2016-12-28 10:41
 * 邮箱: panhongzhi02@163.com
 * 功能描述：执行单服务
 */

public interface ExelistService {
    /**
     * 校验医嘱状态
     * @param anam_id 病案号
     * @param series 住院次数
     * @param dic_code 医嘱号
     * @param date 执行日期
     * @param time 执行时间
     * @return
     */
    @GET("exelist/checkOrder")
    Observable<ScanResponse> checkOrder(
            @Query("anam_id")String anam_id,
            @Query("series")String series,
            @Query("dic_code")String dic_code,
            @Query("date")String date,
            @Query("time")String time
    );

}
