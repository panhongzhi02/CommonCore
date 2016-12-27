package com.taiji.cc.patient.presenter;

import com.taiji.cc.patient.data.Patient;
import com.taiji.library.mvp.BasePresenter;
import com.taiji.library.mvp.BaseView;

import java.util.List;

/**
 * 作者：panho on 2016-12-26 11:16
 * 邮箱: panhongzhi02@163.com
 * 功能描述：视图和业务契约管理器
 */

public interface PatientContract {

    /**
     * 视图处理
     */
    interface View extends BaseView<Presenter>{
        /**
         * 显示患者列表
         * @param patients 患者列表信息
         */
        void showPatientList(List<Patient> patients);

        /**
         * 是否显示加载视图
         * @param isShow true显示 false不显示
         */
        void showLoadView(boolean isShow);

        /**
         * 错误提示
         * @param errorMessage 错误信息
         */
        void showError(String errorMessage);

        /**
         * 当数据为空时的处理
         */
        void showNoData();

        /**
         * 加载完成
         */
        void loadComplate();

    }

    /**
     * 业务处理
     */
    interface Presenter extends BasePresenter{
        /**
         * 加载患者信息
         */
        void loadPatientData(String area_id);

    }
}
