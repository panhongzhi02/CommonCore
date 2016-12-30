package com.taiji.cc.order.presenter;

import com.taiji.cc.order.data.bean.Order;
import com.taiji.library.mvp.BasePresenter;
import com.taiji.library.mvp.BaseView;

import java.util.List;

/**
 * 作者：panho on 2016-12-28 11:01
 * 邮箱: panhongzhi02@163.com
 * 功能描述：医嘱视图和业务逻辑契约
 */

public interface OrderContract {

    interface View extends BaseView<Presenter>{
        /**
         * 显示医嘱列表
         * @param orders
         */
        void showOrderList(List<Order> orders);
        /**
         * 是否显示加载视图
         * @param isShow true显示 false不显示
         */
        void showLoadView(boolean isShow);
        /**
         * 显示医嘱详情
         * @param order
         */
        void showOrderDetails(Order order);

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

    interface Presenter extends BasePresenter{
        /**
         * 加载患者信息
         */
        void loadOrderData(String anam_id,String series);

    }

}
