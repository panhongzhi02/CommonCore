package com.taiji.cc.order.presenter;

import android.support.annotation.NonNull;

import com.taiji.cc.order.data.OrderRepository;
import com.taiji.library.mvp.schedulers.BaseSchedulerProvider;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * 作者：panho on 2016-12-28 10:30
 * 邮箱: panhongzhi02@163.com
 * 功能描述：医嘱业务逻辑处理器
 */

public class OrderPresenter implements OrderContract.Presenter{
    @NonNull
    private final OrderRepository mOrderRepository;
    @NonNull
    private final OrderContract.View mView;
    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;
    @NonNull
    private CompositeSubscription mSubscription;

    public OrderPresenter(@NonNull OrderRepository orderRepository,
                          @NonNull OrderContract.View view,
                          @NonNull BaseSchedulerProvider schedulerProvider){
        mOrderRepository = checkNotNull(orderRepository,"orderRepository不可为空");
        mView = checkNotNull(view,"view不可为空");
        mSchedulerProvider = checkNotNull(schedulerProvider,"mSchedulerProvider不可为空");
        mSubscription = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadOrderData(String anam_id, String series) {

    }

    private void loadOrderData(final String anam_id,final String series,final boolean showLoadingUI){
        mView.showLoadView(showLoadingUI);
        mSubscription.clear();


    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

}
