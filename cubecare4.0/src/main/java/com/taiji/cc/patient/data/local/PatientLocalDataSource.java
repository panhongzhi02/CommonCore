package com.taiji.cc.patient.data.local;

import android.content.Context;

import com.taiji.cc.patient.data.Patient;
import com.taiji.cc.patient.data.PatientDataSource;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.data.cache.CacheManager;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-26 11:13
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class PatientLocalDataSource implements PatientDataSource{

    private static PatientLocalDataSource INSTANCE;

    private PatientDBManager mPatientDBManager;

    private Context mContext;

    private PatientLocalDataSource(Context context){
        mPatientDBManager = PatientDBManager.getmInstance(context);
        this.mContext = context;
    }

    public static PatientLocalDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new PatientLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Patient>> getPatients(String area_id) {
        return mPatientDBManager.queryPatients(area_id);
    }

    @Override
    public Observable<Patient> getPatient(String anam_id, String series) {
        return mPatientDBManager.queryPatient(anam_id, series);
    }

    @Override
    public void savePatient(Patient patient) {
        mPatientDBManager.insert(patient);
    }

    @Override
    public void savePatients(List<Patient> patients) {
        mPatientDBManager.insertList(patients);
    }

    @Override
    public void deleteAll() {
        mPatientDBManager.deleteAll();
    }

    @Override
    public void deleteByAreaId(String area_id) {
        mPatientDBManager.deleteByAreaId(area_id);
    }

    @Override
    public void setCache(Cache cache) {
        Cache oldCache = CacheManager.getInstance(mContext).queryCache(cache.getCacheName());
        if(oldCache!=null){
            oldCache.setLastTime(cache.getLastTime());
            oldCache.setValidity(cache.getValidity());
        }else {
            oldCache = cache;
        }
        CacheManager.getInstance(mContext).insertReplace(oldCache);
    }
}
