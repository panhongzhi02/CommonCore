package com.taiji.cc.patient.presenter;

import android.support.annotation.NonNull;

import com.taiji.cc.patient.data.Patient;
import com.taiji.cc.patient.data.PatientRepository;
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
 * 作者：panho on 2016-12-26 11:16
 * 邮箱: panhongzhi02@163.com
 * 功能描述：患者列表功能业务处理器
 */

public class PatientPresenter implements PatientContract.Presenter{
    @NonNull
    private final PatientRepository mPatientRepository;
    @NonNull
    private final PatientContract.View mView;
    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;
    @NonNull
    private CompositeSubscription mSubscription;

    public PatientPresenter(@NonNull PatientRepository patientRepository,
                            @NonNull PatientContract.View view,
                            @NonNull BaseSchedulerProvider schedulerProvider){
        mPatientRepository = checkNotNull(patientRepository,"patientRepository不可为空");
        mView = checkNotNull(view,"view不可为空");
        mSchedulerProvider = checkNotNull(schedulerProvider,"mSchedulerProvider不可为空");
        mSubscription = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

    @Override
    public void loadPatientData(String area_id) {
        try {
            loadPatientData(area_id,true);
        } catch (Exception e) {
            e.printStackTrace();
            mView.showError(e.getMessage());
        }
    }

    /**
     *
     * @param area_id 病区号
     * @param showLoadingUI 是否显示加载进度条
     */
    private void loadPatientData(final String area_id,final boolean showLoadingUI) throws Exception {
        mView.showLoadView(showLoadingUI);
        mSubscription.clear();
        Subscription subscription = mPatientRepository
                .getPatients(area_id)
                .flatMap(new Func1<List<Patient>, Observable<Patient>>() {
                    @Override
                    public Observable<Patient> call(List<Patient> patients) {
                        //过程处理
                        return Observable.from(patients);
                    }
                })
                .filter(new Func1<Patient, Boolean>() {
                    @Override
                    public Boolean call(Patient patient) {
                        //过滤操作
                        return true;
                    }
                })
                .toList()
                .subscribeOn(mSchedulerProvider.io())
                .unsubscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        //终止操作符
                    }
                })
                .subscribe(new Observer<List<Patient>>() {
                    @Override
                    public void onCompleted() {
                        mView.showLoadView(false);
                        mView.loadComplate();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.showLoadView(false);
                        mView.loadComplate();
                        try {
                            mView.showPatientList(mPatientRepository.getLocalPatients(area_id));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<Patient> patients) {
                        if(patients.isEmpty()){
                            mView.showNoData();
                        }else {
                            mView.showPatientList(patients);
                        }
                    }
                });
        mSubscription.add(subscription);
    }

}
