package com.taiji.app.mvp.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.mvp.data.source.ReportRepository;
import com.taiji.app.mvp.data.source.local.ReportLocalDataSource;
import com.taiji.app.mvp.data.source.remote.ReportRemoteDataSource;
import com.taiji.library.mvp.schedulers.SchedulerProvider;
import com.taiji.library.ui.activity.BaseActivity;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public class ReportActivity extends BaseActivity implements ReportContract.View{
    @NonNull
    private ReportContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ReportPresenter(
                ReportRepository.getInstance(ReportRemoteDataSource.getInstance(this),
                        ReportLocalDataSource.getInstance(this)),
                this,
                SchedulerProvider.getInstance()
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode,resultCode);
    }

    @Override
    public void setPresenter(@NonNull ReportContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showReportList(List<Report> reports) {

    }

    @Override
    public void showReportDetails(Report report) {

    }

    @Override
    public void showLoadCompleted() {

    }

    @Override
    public void showLoadView(boolean isShow) {

    }

    @Override
    public void showLoadError() {

    }

    @Override
    public void showNoReport() {

    }

}
