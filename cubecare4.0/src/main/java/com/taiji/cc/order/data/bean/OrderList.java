package com.taiji.cc.order.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.taiji.cc.greendao.db.DaoSession;
import com.taiji.cc.greendao.db.OrderDao;
import com.taiji.cc.greendao.db.OrderListDao;

/**
 * 作者：panho on 2016-12-28 16:53
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */
@Entity
public class OrderList {
    private String anam_id;
    private String series;
    private String area_id;
    private String dic_code;
    private String fun_type;
    private String ini_dt;
    private String usage_name;
    private String nums_mean;
    private String use_label;
    private String state;
    @ToMany(joinProperties = {
            @JoinProperty(name = "anam_id",referencedName = "anam_id"),
            @JoinProperty(name = "series",referencedName = "series"),
            @JoinProperty(name = "area_id",referencedName = "area_id"),
            @JoinProperty(name = "dic_code",referencedName = "dic_code")
    })
    private List<Order> orders;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1966227481)
    private transient OrderListDao myDao;
    @Generated(hash = 1223163619)
    public OrderList(String anam_id, String series, String area_id, String dic_code,
            String fun_type, String ini_dt, String usage_name, String nums_mean,
            String use_label, String state) {
        this.anam_id = anam_id;
        this.series = series;
        this.area_id = area_id;
        this.dic_code = dic_code;
        this.fun_type = fun_type;
        this.ini_dt = ini_dt;
        this.usage_name = usage_name;
        this.nums_mean = nums_mean;
        this.use_label = use_label;
        this.state = state;
    }
    @Generated(hash = 1767955754)
    public OrderList() {
    }
    public String getAnam_id() {
        return this.anam_id;
    }
    public void setAnam_id(String anam_id) {
        this.anam_id = anam_id;
    }
    public String getSeries() {
        return this.series;
    }
    public void setSeries(String series) {
        this.series = series;
    }
    public String getArea_id() {
        return this.area_id;
    }
    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
    public String getDic_code() {
        return this.dic_code;
    }
    public void setDic_code(String dic_code) {
        this.dic_code = dic_code;
    }
    public String getFun_type() {
        return this.fun_type;
    }
    public void setFun_type(String fun_type) {
        this.fun_type = fun_type;
    }
    public String getIni_dt() {
        return this.ini_dt;
    }
    public void setIni_dt(String ini_dt) {
        this.ini_dt = ini_dt;
    }
    public String getUsage_name() {
        return this.usage_name;
    }
    public void setUsage_name(String usage_name) {
        this.usage_name = usage_name;
    }
    public String getNums_mean() {
        return this.nums_mean;
    }
    public void setNums_mean(String nums_mean) {
        this.nums_mean = nums_mean;
    }
    public String getUse_label() {
        return this.use_label;
    }
    public void setUse_label(String use_label) {
        this.use_label = use_label;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1984602447)
    public List<Order> getOrders() {
        if (orders == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrderDao targetDao = daoSession.getOrderDao();
            List<Order> ordersNew = targetDao._queryOrderList_Orders(anam_id,
                    series, area_id, dic_code);
            synchronized (this) {
                if (orders == null) {
                    orders = ordersNew;
                }
            }
        }
        return orders;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1446109810)
    public synchronized void resetOrders() {
        orders = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2064088218)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderListDao() : null;
    }

}
