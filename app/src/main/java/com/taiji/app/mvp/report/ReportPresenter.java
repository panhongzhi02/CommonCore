package com.taiji.app.mvp.report;

import android.support.annotation.NonNull;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.mvp.data.source.ReportRepository;
import com.taiji.library.mvp.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public class ReportPresenter implements ReportContract.Presenter{
    @NonNull
    private final ReportRepository mReportRepository;
    @NonNull
    private final ReportContract.View mReportView;
    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;
    @NonNull
    private CompositeSubscription mSubscription;

    private boolean mFirstLoad = true;//是否首次加载

    public ReportPresenter(@NonNull ReportRepository reportRepository,
                           @NonNull ReportContract.View reportView,
                           @NonNull BaseSchedulerProvider schedulerProvider){
        mReportRepository = checkNotNull(reportRepository,"reportRepository不可为空");
        mReportView = checkNotNull(reportView,"reportView不可为空");
        mSchedulerProvider = checkNotNull(schedulerProvider,"schedulerProvider不可为空");
        mSubscription = new CompositeSubscription();
        mReportView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void createReport() {

    }

    @Override
    public void deleteReport() {

    }

    @Override
    public void updateReport() {

    }

    @Override
    public void loadReportData(boolean forceUpdate) {

    }

    /**
     *
     * @param forceUpdate 是否强制更新
     * @param showLoadingUI 是否显示加载控件
     */
    private void loadReportData(final boolean forceUpdate,final boolean showLoadingUI){
        mReportView.showLoadView(showLoadingUI);
        if(forceUpdate){
            mReportRepository.refreshReports();
        }

        mSubscription.clear();
        Subscription subscription = mReportRepository
                .getReports()
                .flatMap(new Func1<List<Report>, Observable<Report>>() {
                    @Override
                    public Observable<Report> call(List<Report> reports) {
                        return Observable.from(reports);
                    }
                })
                .filter(new Func1<Report, Boolean>() {
                    @Override
                    public Boolean call(Report report) {
                        //执行数据过滤操作
                        return true;
                    }
                })
                .toList()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        //终止操作
                    }
                })
                .subscribe(new Observer<List<Report>>() {
                    @Override
                    public void onCompleted() {
                        mReportView.showLoadView(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mReportView.showLoadError();
                    }

                    @Override
                    public void onNext(List<Report> reports) {
                        if(reports.isEmpty()){
                            mReportView.showNoReport();
                        }else {
                            mReportView.showReportList(reports);
                        }
                    }
                });
        mSubscription.add(subscription);
    }

}
