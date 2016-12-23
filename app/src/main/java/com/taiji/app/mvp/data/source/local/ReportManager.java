package com.taiji.app.mvp.data.source.local;

import android.content.Context;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.greendao.db.DaoSession;
import com.taiji.app.greendao.db.ReportDao;
import com.taiji.app.greendao.manager.BaseDBManager;
import com.taiji.app.greendao.manager.TestDBManager;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-23
 */

public class ReportManager extends BaseDBManager {

    private static ReportManager mInstance;

    public static ReportManager getmInstance(Context context){
        if (mInstance == null) {
            synchronized (TestDBManager.class) {
                mInstance = new ReportManager(context);
            }
        }
        return mInstance;
    }

    protected ReportManager(Context context) {
        super(context);
    }

    public Observable<Void> insert(Report report){
        DaoSession session = getReadableSession();
        ReportDao dao = session.getReportDao();
        RxDao rxDao = dao.rx();
        return rxDao.insert(report);
    }

    public Observable<Void> insertPL(List<Report> reports){
        DaoSession session = getReadableSession();
        ReportDao dao = session.getReportDao();
        RxDao rxDao = dao.rx();
        return rxDao.insertInTx(reports);
    }

    public Observable<List<Report>> queryReports(){
        DaoSession session = getReadableSession();
        ReportDao dao = session.getReportDao();
        QueryBuilder<Report> queryBuilder = dao.queryBuilder();
        RxQuery rxQuery = queryBuilder.rx();
        return rxQuery.list();
    }


}
