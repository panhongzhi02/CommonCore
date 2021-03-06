package com.taiji.app.greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.taiji.app.greendao.bean.Report;
import com.taiji.app.greendao.bean.Test;

import com.taiji.app.greendao.db.ReportDao;
import com.taiji.app.greendao.db.TestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig reportDaoConfig;
    private final DaoConfig testDaoConfig;

    private final ReportDao reportDao;
    private final TestDao testDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        reportDaoConfig = daoConfigMap.get(ReportDao.class).clone();
        reportDaoConfig.initIdentityScope(type);

        testDaoConfig = daoConfigMap.get(TestDao.class).clone();
        testDaoConfig.initIdentityScope(type);

        reportDao = new ReportDao(reportDaoConfig, this);
        testDao = new TestDao(testDaoConfig, this);

        registerDao(Report.class, reportDao);
        registerDao(Test.class, testDao);
    }
    
    public void clear() {
        reportDaoConfig.clearIdentityScope();
        testDaoConfig.clearIdentityScope();
    }

    public ReportDao getReportDao() {
        return reportDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

}
