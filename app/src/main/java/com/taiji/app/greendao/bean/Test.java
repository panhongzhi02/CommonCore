package com.taiji.app.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-11-18
 */
@Entity
public class Test {

    @Id
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Generated(hash = 2029829520)
    public Test(String id) {
        this.id = id;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

}
