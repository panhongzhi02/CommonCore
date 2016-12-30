package com.taiji.cc.patient.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiji.cc.R;
import com.taiji.cc.patient.adapter.PatientAdapter;
import com.taiji.cc.patient.data.Patient;
import com.taiji.cc.patient.data.PatientRepository;
import com.taiji.cc.patient.data.local.PatientLocalDataSource;
import com.taiji.cc.patient.data.remote.PatientRemoteDataSource;
import com.taiji.cc.patient.presenter.PatientContract;
import com.taiji.cc.patient.presenter.PatientPresenter;
import com.taiji.library.data.cache.CacheManager;
import com.taiji.library.mvp.schedulers.SchedulerProvider;
import com.taiji.library.ui.decoration.HorizontalDividerItemDecoration;
import com.taiji.library.ui.fragment.BaseFragment;
import com.taiji.library.util.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 作者：panho on 2016-12-26 10:51
 * 邮箱: panhongzhi02@163.com
 * 功能描述：患者列表视图
 */
public class PatientFragment extends BaseFragment implements PatientContract.View {

    @BindView(R.id.patient_rv)
    RecyclerView mPatientRv;
    @BindView(R.id.patients_sr)
    SwipeRefreshLayout mPatientsSr;

    private PatientContract.Presenter mPresenter;

    private PatientAdapter mPatientAdapter;

    public PatientFragment() {

    }

    public static PatientFragment newInstance() {
        return new PatientFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PatientPresenter(
                PatientRepository.getInstance(mActivity,PatientRemoteDataSource.getInstance(mActivity), PatientLocalDataSource.getInstance(mActivity)),
                this,
                SchedulerProvider.getInstance());
        mPatientAdapter = new PatientAdapter(new ArrayList<Patient>(0));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.patient_fragment, container, false);
        ButterKnife.bind(this, root);
        mPatientsSr.setColorSchemeResources(R.color.colorPrimary);
        mPatientsSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadPatientData("112");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPatientRv.setLayoutManager(layoutManager);
        mPatientRv.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(mActivity)
                        .colorResId(R.color.colorPrimary)
                        .showLastDivider()
                        .marginResId(R.dimen.listview_line_margin)
                        .sizeResId(R.dimen.listview_line)
                        .build()
        );
        mPatientRv.setAdapter(mPatientAdapter);
        mPresenter.loadPatientData("112");
        return root;
    }

    @Override
    public void showPatientList(List<Patient> patients) {
        mPatientAdapter.setNewData(patients);
    }

    @Override
    public void showLoadView(boolean isShow) {
        if(isShow){
            mActivity.showProgress();
        }else {
            mActivity.hideProgress();
        }
    }

    @Override
    public void showError(String errorMessage) {
        T.showShort(mActivity,errorMessage);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void loadComplate() {
        mPatientsSr.setRefreshing(false);
    }

    @Override
    public void setPresenter(PatientContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


}
