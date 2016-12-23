package com.taiji.app.mvp.data.source.local;

import android.content.Context;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.mvp.data.source.ReportDataSource;

import java.util.List;

import rx.Observable;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public class ReportLocalDataSource implements ReportDataSource{

    private static ReportLocalDataSource INSTANCE;

    private ReportManager mManager;

    private ReportLocalDataSource(Context context) {
        mManager = ReportManager.getmInstance(context);
    }

    public static ReportLocalDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ReportLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Report>> getReports() {
        return mManager.queryReports();
    }

    @Override
    public Observable<Report> getReport(String id) {
        return null;
    }

    @Override
    public void saveReport(Report report) {

    }

    @Override
    public void deleteAllReports() {

    }

    @Override
    public void refreshReports() {

    }
}
