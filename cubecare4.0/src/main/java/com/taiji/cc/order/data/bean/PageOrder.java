package com.taiji.cc.order.data.bean;

import java.util.List;

/**
 * 作者：panho on 2016-12-28 10:20
 * 邮箱: panhongzhi02@163.com
 * 功能描述：
 */

public class PageOrder {

    private String total;//总行数

    private List<OrderList> orderLists;//医嘱列表

    public PageOrder(String total, List<OrderList> orderLists) {
        this.total = total;
        this.orderLists = orderLists;
    }

    public PageOrder() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @Override
    public String toString() {
        return "PageOrder{" +
                "total='" + total + '\'' +
                ", orderLists=" + orderLists +
                '}';
    }
}
