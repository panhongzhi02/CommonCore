package com.taiji.app.greendao.manager;

import android.content.Context;

import com.taiji.app.greendao.bean.Test;
import com.taiji.app.greendao.db.DaoSession;
import com.taiji.app.greendao.db.TestDao;
import com.taiji.library.ui.page.base.PageBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-18
 */
public class TestDBManager extends BaseDBManager {

    private static TestDBManager mInstance;

    public static TestDBManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (TestDBManager.class) {
                mInstance = new TestDBManager(context);
            }
        }
        return mInstance;
    }

    protected TestDBManager(Context context) {
        super(context);
    }

    public void insertList(List<Test> tests){
        DaoSession session = getWritableSession();
        TestDao dao = session.getTestDao();
        dao.insertOrReplaceInTx(tests);
    }


    public int queryCount(){
        DaoSession session = getReadableSession();
        TestDao dao = session.getTestDao();
        QueryBuilder<Test> qb = dao.queryBuilder();
        return (int) qb.count();
    }

    public PageBean<Test> queryPage(int start,int limit){
        DaoSession session = getReadableSession();
        TestDao dao = session.getTestDao();
        QueryBuilder<Test> qb = dao.queryBuilder();
        qb.orderAsc(TestDao.Properties.Id).offset(start).limit(limit);
        PageBean<Test> pageBean = new PageBean<>(queryCount(),qb.list());
        return pageBean;
    }

}
