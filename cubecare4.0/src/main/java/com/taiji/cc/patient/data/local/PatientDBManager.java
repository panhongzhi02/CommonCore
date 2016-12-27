package com.taiji.cc.patient.data.local;

import android.content.Context;

import com.taiji.cc.greendao.db.BaseDBManager;
import com.taiji.cc.greendao.db.DaoSession;
import com.taiji.cc.greendao.db.PatientDao;
import com.taiji.cc.patient.data.Patient;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-26 14:15
 * 邮箱: panhongzhi02@163.com
 * 功能描述：本地患者表操作类
 */
public class PatientDBManager extends BaseDBManager {

    private static PatientDBManager mInstance;

    public static PatientDBManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (PatientDBManager.class) {
                mInstance = new PatientDBManager(context);
            }
        }
        return mInstance;
    }

    protected PatientDBManager(Context context) {
        super(context);
    }

    public Observable<Void> insert(Patient patient){
        DaoSession session = getWritableSession();
        PatientDao dao = session.getPatientDao();
        RxDao rxDao = dao.rx();
        return rxDao.insert(patient);
    }

    public Observable<Void> insertList(List<Patient> patients){
        DaoSession session = getWritableSession();
        PatientDao dao = session.getPatientDao();
        RxDao rxDao = dao.rx();
        return rxDao.insertInTx(patients);
    }

    public Observable<List<Patient>> queryPatients(String area_id){
        DaoSession session = getReadableSession();
        PatientDao dao = session.getPatientDao();
        QueryBuilder<Patient> queryBuilder = dao.queryBuilder();
        RxQuery rxQuery = queryBuilder.where(PatientDao.Properties.Pp_wardCode.eq(area_id)).rx();
        return rxQuery.list();
    }

    public Observable<Patient> queryPatient(String anam_id,String series){
        DaoSession session = getReadableSession();
        PatientDao dao = session.getPatientDao();
        QueryBuilder<Patient> queryBuilder = dao.queryBuilder();
        RxQuery rxQuery = queryBuilder.where(PatientDao.Properties.P_id.eq(anam_id),PatientDao.Properties.Pp_visitid.eq(series)).rx();
        return  rxQuery.unique();
    }

    public void deleteByAreaId(String area_id){
        DaoSession session = getWritableSession();
        PatientDao dao = session.getPatientDao();
        QueryBuilder<Patient> queryBuilder = dao.queryBuilder();
        DeleteQuery<Patient> deleteQuery = queryBuilder.where(PatientDao.Properties.Pp_wardCode.eq(area_id)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public Observable<Void> deleteAll(){
        DaoSession session = getWritableSession();
        PatientDao dao = session.getPatientDao();
        RxDao rxDao = dao.rx();
        return rxDao.deleteAll();
    }

}
