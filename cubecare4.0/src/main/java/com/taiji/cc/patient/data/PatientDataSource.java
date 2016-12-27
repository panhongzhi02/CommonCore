package com.taiji.cc.patient.data;

import com.taiji.library.data.cache.Cache;

import java.util.List;

import rx.Observable;

/**
 * 作者：panho on 2016-12-26 11:09
 * 邮箱: panhongzhi02@163.com
 * 功能描述：患者信息数据源接口
 */

public interface PatientDataSource {

    /**
     * 获取患者列表
     * @return
     */
    Observable<List<Patient>> getPatients(String area_id);

    /**
     * 获取单个患者信息
     * @param anam_id 病案号
     * @param series 住院次数
     * @return
     */
    Observable<Patient> getPatient(String anam_id,String series);

    void savePatient(Patient patient);

    void savePatients(List<Patient> patients);

    /**
     * 删除全部患者表数据
     */
    void deleteAll();

    /**
     * 删除某一病区数据
     * @param area_id
     */
    void deleteByAreaId(String area_id);

    /**
     * 设置缓存有效时间
     * @param cache
     */
    void setCache(Cache cache);

}
