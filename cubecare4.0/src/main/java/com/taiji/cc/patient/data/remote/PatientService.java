package com.taiji.cc.patient.data.remote;

import com.taiji.cc.patient.data.Patient;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：panho on 2016-12-26 15:23
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public interface PatientService {
    /**
     *
     * @param area_id
     * @return
     */
    @GET("patientInfo/getPatientInfo")
    Observable<List<Patient>> getPatients(@Query("area_id")String area_id);

}
