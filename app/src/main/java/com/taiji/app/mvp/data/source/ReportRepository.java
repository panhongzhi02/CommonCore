package com.taiji.app.mvp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.taiji.app.greendao.bean.Report;
import com.taiji.library.mvp.schedulers.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public class ReportRepository implements ReportDataSource{

    @Nullable
    private static ReportRepository INSTANCE = null;
    @Nullable
    private final ReportDataSource mReportRemoteDataSource;
    @Nullable
    private final ReportDataSource mReportLocalDataSource;

    private List<Report> mReports;
    /**
     * 标志着缓存无效,迫使一个下次更新数据请求。这个变量
     * 包当地的可见性,因此它可以访问测试。
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;

    private ReportRepository(@NonNull ReportDataSource reportRemoteDataSource,
                               @NonNull ReportDataSource reportLocalDataSource){
        mReportRemoteDataSource = checkNotNull(reportLocalDataSource);
        mReportLocalDataSource = checkNotNull(reportRemoteDataSource);
    }

    public static ReportRepository getInstance(@NonNull ReportDataSource reportRemoteDataSource,
                                               @NonNull ReportDataSource reportLocalDataSource){
        if(INSTANCE == null){
            INSTANCE = new ReportRepository(reportRemoteDataSource,reportLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    };

    @Override
    public Observable<List<Report>> getReports() {
        //本地存在数据并且缓存可用
        if(mReports!=null&&!mCacheIsDirty){
            return Observable.from(mReports).toList();
        }else if(mReports==null){
            mReports = new ArrayList<>();
        }
        Observable<List<Report>> remoteReport = getAndSaveRemoteReport();
        if(mCacheIsDirty){
            return remoteReport;
        }else {
            Observable<List<Report>> localReport = getAndCacheLocalReport();
            return Observable.concat(localReport,remoteReport)
                    .filter(new Func1<List<Report>, Boolean>() {
                        @Override
                        public Boolean call(List<Report> reports) {
                            return !reports.isEmpty();
                        }
                    }).first();
        }
    }

    /**
     * 获取远程数据并保存到本地
     * @return
     */
    private Observable<List<Report>> getAndSaveRemoteReport(){
        return mReportRemoteDataSource
                .getReports()
                .flatMap(new Func1<List<Report>, Observable<List<Report>>>() {

                    @Override
                    public Observable<List<Report>> call(final List<Report> reports) {
                        return Observable.from(reports).doOnNext(new Action1<Report>() {
                            @Override
                            public void call(Report report) {
                                mReportLocalDataSource.saveReport(report);
                                reports.add(report);
                            }
                        }).toList();
                    }
                })
                .subscribeOn(SchedulerProvider.getInstance().io())
                .unsubscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                       mCacheIsDirty = false;
                    }
                });
    }

    /**
     * 缓存本地数据
     * @return
     */
    private Observable<List<Report>> getAndCacheLocalReport() {
        return mReportLocalDataSource.getReports()
                .flatMap(new Func1<List<Report>, Observable<List<Report>>>() {
                    @Override
                    public Observable<List<Report>> call(List<Report> reports) {
                        return Observable.from(reports)
                                .doOnNext(new Action1<Report>() {
                                    @Override
                                    public void call(Report report) {
                                        mReports.add(report);
                                    }
                                })
                                .toList();
                    }
                });
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
        mCacheIsDirty = true;
    }

}
