package com.taiji.cc.order.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：panho on 2016-12-28 10:12
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */
@Entity
public class Order {
    private String anam_id;//病案号
    private String series;//住院次数
    private String area_id;//病区号
    private String advi_type;//医嘱类型
    private String fun_type;//长期临时
    private String state;//停止执行
    private String dic_code;//主医嘱号
    private String advi_id;//子医嘱号
    private String dic_name;//医嘱内容
    private String spec;//规格
    private String one_quan;//剂量
    private String advi_unit;//单位
    private String usage_name;//用药途径
    private String note;//备注
    private String ini_dt;//开医嘱时间
    private String stop_dt;//停医嘱时间
    private String nums_mean;//频次
    private String office_code;//科室代码
    private String ini_doctor;//开医嘱医生
    private String ini_nurse;//开医嘱护士
    private String stop_doctor;//停医嘱医生
    private String stop_nurse;//停医嘱护士
    private String use_label;//医嘱执行时间
    @Generated(hash = 1836044692)
    public Order(String anam_id, String series, String area_id, String advi_type,
            String fun_type, String state, String dic_code, String advi_id,
            String dic_name, String spec, String one_quan, String advi_unit,
            String usage_name, String note, String ini_dt, String stop_dt,
            String nums_mean, String office_code, String ini_doctor,
            String ini_nurse, String stop_doctor, String stop_nurse,
            String use_label) {
        this.anam_id = anam_id;
        this.series = series;
        this.area_id = area_id;
        this.advi_type = advi_type;
        this.fun_type = fun_type;
        this.state = state;
        this.dic_code = dic_code;
        this.advi_id = advi_id;
        this.dic_name = dic_name;
        this.spec = spec;
        this.one_quan = one_quan;
        this.advi_unit = advi_unit;
        this.usage_name = usage_name;
        this.note = note;
        this.ini_dt = ini_dt;
        this.stop_dt = stop_dt;
        this.nums_mean = nums_mean;
        this.office_code = office_code;
        this.ini_doctor = ini_doctor;
        this.ini_nurse = ini_nurse;
        this.stop_doctor = stop_doctor;
        this.stop_nurse = stop_nurse;
        this.use_label = use_label;
    }
    @Generated(hash = 1105174599)
    public Order() {
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
    public String getAdvi_type() {
        return this.advi_type;
    }
    public void setAdvi_type(String advi_type) {
        this.advi_type = advi_type;
    }
    public String getFun_type() {
        return this.fun_type;
    }
    public void setFun_type(String fun_type) {
        this.fun_type = fun_type;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDic_code() {
        return this.dic_code;
    }
    public void setDic_code(String dic_code) {
        this.dic_code = dic_code;
    }
    public String getAdvi_id() {
        return this.advi_id;
    }
    public void setAdvi_id(String advi_id) {
        this.advi_id = advi_id;
    }
    public String getDic_name() {
        return this.dic_name;
    }
    public void setDic_name(String dic_name) {
        this.dic_name = dic_name;
    }
    public String getSpec() {
        return this.spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public String getOne_quan() {
        return this.one_quan;
    }
    public void setOne_quan(String one_quan) {
        this.one_quan = one_quan;
    }
    public String getAdvi_unit() {
        return this.advi_unit;
    }
    public void setAdvi_unit(String advi_unit) {
        this.advi_unit = advi_unit;
    }
    public String getUsage_name() {
        return this.usage_name;
    }
    public void setUsage_name(String usage_name) {
        this.usage_name = usage_name;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getIni_dt() {
        return this.ini_dt;
    }
    public void setIni_dt(String ini_dt) {
        this.ini_dt = ini_dt;
    }
    public String getStop_dt() {
        return this.stop_dt;
    }
    public void setStop_dt(String stop_dt) {
        this.stop_dt = stop_dt;
    }
    public String getNums_mean() {
        return this.nums_mean;
    }
    public void setNums_mean(String nums_mean) {
        this.nums_mean = nums_mean;
    }
    public String getOffice_code() {
        return this.office_code;
    }
    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }
    public String getIni_doctor() {
        return this.ini_doctor;
    }
    public void setIni_doctor(String ini_doctor) {
        this.ini_doctor = ini_doctor;
    }
    public String getIni_nurse() {
        return this.ini_nurse;
    }
    public void setIni_nurse(String ini_nurse) {
        this.ini_nurse = ini_nurse;
    }
    public String getStop_doctor() {
        return this.stop_doctor;
    }
    public void setStop_doctor(String stop_doctor) {
        this.stop_doctor = stop_doctor;
    }
    public String getStop_nurse() {
        return this.stop_nurse;
    }
    public void setStop_nurse(String stop_nurse) {
        this.stop_nurse = stop_nurse;
    }
    public String getUse_label() {
        return this.use_label;
    }
    public void setUse_label(String use_label) {
        this.use_label = use_label;
    }
}
