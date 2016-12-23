package com.taiji.app.mvp.report;

import com.taiji.app.greendao.bean.Report;
import com.taiji.library.mvp.BasePresenter;
import com.taiji.library.mvp.BaseView;

import java.util.List;

/**
 * 描述：
 * 创建人： panho
 * 创建时间： 2016-12-22
 */

public interface ReportContract {
    /**
     * 视图处理器
     */
    interface View extends BaseView<Presenter>{
        /**
         * 显示文书列表
         */
        void showReportList(List<Report> reports);
        /**
         * 显示文书详情
         */
        void showReportDetails(Report report);

        /**
         * 显示加载完成
         */
        void showLoadCompleted();

        /**
         * 显示加载框
         * @param isShow 是否显示
         */
        void showLoadView(boolean isShow);

        /**
         * 显示加载错误
         */
        void showLoadError();

        void showNoReport();
    }

    /**
     * 逻辑处理器
     */
    interface Presenter extends BasePresenter{
        /**
         * 获取上个云返回数据
         * @param requestCode
         * @param resultCode
         */
        void result(int requestCode, int resultCode);
        /**
         * 创建文书
         */
        void createReport();
        /**
         * 删除文书
         */
        void deleteReport();
        /**
         * 更新文书
         */
        void updateReport();

        /**
         *  加载文书数据
         * @param forceUpdate 是否强制更新
         */
        void loadReportData(boolean forceUpdate);
    }

}
