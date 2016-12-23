package com.taiji.app.mvp.data.source;

import com.taiji.app.greendao.bean.Report;

import java.util.List;

import rx.Observable;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public interface ReportDataSource {

    Observable<List<Report>> getReports();

    Observable<Report> getReport(String id);

    void saveReport(Report report);

    void deleteAllReports();

    void refreshReports();

}
