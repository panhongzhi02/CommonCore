package com.taiji.cc.patient.data.remote;

import android.content.Context;

import com.blankj.utilcode.utils.TimeUtils;
import com.taiji.cc.patient.data.Patient;
import com.taiji.cc.patient.data.PatientDataSource;
import com.taiji.library.data.cache.Cache;
import com.taiji.library.http.methods.HttpMethod;
import com.taiji.library.util.L;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-26 11:14
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class PatientRemoteDataSource implements PatientDataSource{

    private static PatientRemoteDataSource INSTANCE;

    private Context mContext;

    public PatientRemoteDataSource(Context context) {
        mContext = context;
    }

    public static PatientRemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new PatientRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Patient>> getPatients(String area_id) throws Exception {
        PatientService service = HttpMethod.getInstance().getRetrofit(mContext).create(PatientService.class);
        L.d("patient","从服务器获取患者信息==============时间："+ TimeUtils.getCurTimeString());
        return service.getPatients(area_id);
    }

    @Override
    public Observable<Patient> getPatient(String anam_id, String series) {
        return null;
    }

    @Override
    public void savePatient(Patient patient) {

    }

    @Override
    public void savePatients(List<Patient> patients) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteByAreaId(String area_id) {

    }

    @Override
    public void setCache(Cache cache) {

    }
}
