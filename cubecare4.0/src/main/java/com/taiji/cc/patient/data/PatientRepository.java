package com.taiji.cc.patient.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.TimeUtils;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.data.cache.CacheManager;
import com.taiji.library.mvp.schedulers.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 作者：panho on 2016-12-26 11:10
 * 邮箱: panhongzhi02@163.com
 * 功能描述：患者信息数据处理器
 */

public class PatientRepository implements PatientDataSource{

    @NonNull
    private static PatientRepository INSTANCE = null;
    @NonNull
    private final PatientDataSource mPatientRemoteDataSource;
    @NonNull
    private final PatientDataSource mPatientLocalDataSource;
    //一级缓存
    private List<Patient> mPatients;
    //标志着缓存是否有效  true有效 false无效
    boolean mCacheIsValid = false;

    private Context mContext;

    private PatientRepository(Context context, @NonNull PatientDataSource patientRemoteDataSource,
                              @NonNull PatientDataSource patientLocalDataSource){
        mContext = checkNotNull(context);
        mPatientRemoteDataSource = checkNotNull(patientRemoteDataSource,"mPatientRemoteDataSource不可为空");
        mPatientLocalDataSource = checkNotNull(patientLocalDataSource,"patientLocalDataSource不可为空");
    }

    public static PatientRepository getInstance(Context context,@NonNull PatientDataSource patientRemoteDataSource,
                                                @NonNull PatientDataSource patientLocalDataSource){
        if(INSTANCE==null){
            INSTANCE = new PatientRepository(context,patientRemoteDataSource,patientLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destoryInstance(){
        INSTANCE = null;
    }

    @Override
    public Observable<List<Patient>> getPatients(String area_id) {
        mCacheIsValid = CacheManager.getInstance(mContext).isValid("patient");
        if(mPatients!=null&&mCacheIsValid){
            return Observable.from(mPatients).toList();
        }else if(mPatients==null){
            mPatients = new ArrayList<>();
        }
        Observable<List<Patient>> remotePatient = getAndSaveRemotePatient(area_id);
        if(!mCacheIsValid){
            return remotePatient;
        }else {
            Observable<List<Patient>> localPatient = getAndCacheLocalPatient(area_id);
            return Observable.concat(localPatient,remotePatient).filter(new Func1<List<Patient>, Boolean>() {
                @Override
                public Boolean call(List<Patient> patients) {
                    return !patients.isEmpty();
                }
            }).first();
        }
    }

    /**
     * 本地获取患者列表
     * @return
     */
    private Observable<List<Patient>> getAndCacheLocalPatient(String area_id) {
        return mPatientLocalDataSource.getPatients(area_id).flatMap(new Func1<List<Patient>, Observable<List<Patient>>>() {
            @Override
            public Observable<List<Patient>> call(List<Patient> patients) {
                mPatients.clear();
                mPatients.addAll(patients);
                return Observable.from(patients).toList();
            }
        });
    }

    /**
     * 远程获取患者列表并保存到本地
     * @return
     */
    private Observable<List<Patient>> getAndSaveRemotePatient(final String area_id) {
        return mPatientRemoteDataSource
                .getPatients(area_id)
                .flatMap(new Func1<List<Patient>, Observable<List<Patient>>>() {
                    @Override
                    public Observable<List<Patient>> call(List<Patient> patients) {
                        deleteByAreaId(area_id);
                        mPatientLocalDataSource.savePatients(patients);
                        mPatients.addAll(patients);
                        //设置缓存有效时间
                        mPatientLocalDataSource.setCache(new Cache(
                                null,
                                "patient",
                                60,
                                TimeUtils.getCurTimeString()
                        ));
                        return Observable.from(patients).toList();
                    }
                })
                .subscribeOn(SchedulerProvider.getInstance().io())
                .unsubscribeOn(SchedulerProvider.getInstance().io())
                .observeOn(SchedulerProvider.getInstance().ui())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        mCacheIsValid = true;
                    }
                });

    }

    @Override
    public Observable<Patient> getPatient(final String anam_id, final String series) {
        checkNotNull(anam_id);
        checkNotNull(series);
        final Patient patient = getPatientWithId(anam_id, series);
        if(patient!=null){
            return Observable.just(patient);
        }

        if(mPatients==null){
            mPatients = new ArrayList<>();
        }

        Observable<Patient> localPatient = getPatientWithIdFromLocalRepository(anam_id, series);
        Observable<Patient> remotePatient = mPatientRemoteDataSource
                .getPatient(anam_id, series)
                .doOnNext(new Action1<Patient>() {
                    @Override
                    public void call(Patient patient) {
                        mPatientLocalDataSource.savePatient(patient);
                        mPatients.add(patient);
                    }
                });
        return Observable.concat(localPatient,remotePatient).first()
                .map(new Func1<Patient, Patient>() {
                    @Override
                    public Patient call(Patient patient) {
                        if(patient==null){
                            throw new NoSuchElementException("通过病案号:"+anam_id+"和住院次数："+series+"未找到患者");
                        }
                        return patient;
                    }
                });
    }

    /**
     * 在本地数据源查找患者
     * @param anam_id 病案号
     * @param series 住院次数
     * @return
     */
    private Observable<Patient> getPatientWithIdFromLocalRepository(
            @NonNull final String anam_id,
            @NonNull final String series) {
        return mPatientLocalDataSource
                .getPatient(anam_id,series)
                .doOnNext(new Action1<Patient>() {
                    @Override
                    public void call(Patient patient) {
                        mPatients.add(patient);
                    }
                })
                .first();
    }

    /**
     * 在缓存中查找患者
     * @param anam_id 病案号
     * @param series 住院次数
     * @return
     */
    private Patient getPatientWithId(String anam_id, String series){
        checkNotNull(anam_id);
        checkNotNull(series);
        if(mPatients!=null&&mCacheIsValid){
            for (int i = 0; i < mPatients.size(); i++) {
                if(mPatients.get(i).getP_id().equals(anam_id)
                        &&mPatients.get(i).getPp_visitid().equals(series)){
                    return mPatients.get(i);
                }
            }
            return null;
        }else {
            return null;
        }
    }

    @Override
    public void savePatient(Patient patient) {
        checkNotNull(patient);
        mPatientLocalDataSource.savePatient(patient);
        mPatientRemoteDataSource.savePatient(patient);
        if(mPatients == null){
            mPatients = new ArrayList<>();
        }
        mPatients.add(patient);
    }

    @Override
    public void savePatients(List<Patient> patients) {
        checkNotNull(patients);
    }

    @Override
    public void deleteAll() {
        mPatientLocalDataSource.deleteAll();
        mPatients.clear();
    }

    @Override
    public void deleteByAreaId(String area_id) {
        mPatientLocalDataSource.deleteByAreaId(area_id);
        mPatients.clear();
    }

    @Override
    public void setCache(Cache cache) {

    }
}
