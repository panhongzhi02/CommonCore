package com.taiji.app.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */
@Entity
public class Report {
    @Id
    private Long id;

    private String tid;

    private String tname;

    public String getTname() {
        return this.tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 75188343)
    public Report(Long id, String tid, String tname) {
        this.id = id;
        this.tid = tid;
        this.tname = tname;
    }

    @Generated(hash = 1739299007)
    public Report() {
    }



}
