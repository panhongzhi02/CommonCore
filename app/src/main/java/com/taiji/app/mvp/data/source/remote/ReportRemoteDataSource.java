package com.taiji.app.mvp.data.source.remote;

import android.content.Context;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.http.service.ReportService;
import com.taiji.app.mvp.data.source.ReportDataSource;
import com.taiji.library.http.methods.HttpMethod;
import com.taiji.library.mvp.schedulers.SchedulerProvider;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public class ReportRemoteDataSource implements ReportDataSource{

    private static ReportRemoteDataSource INSTANCE;

    private Context mContext;

    private ReportRemoteDataSource(Context context){
        this.mContext = context;
    }

    public static ReportRemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ReportRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Report>> getReports() {
        ReportService service = null;
        try {
            service = HttpMethod.getInstance().getRetrofit(mContext).create(ReportService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service.getReports();
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
