package com.taiji.app.http.service;

import com.taiji.app.greendao.bean.Report;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-23
 */

public interface ReportService {

    @GET("report/getReports")
    Observable<List<Report>> getReports();

}
