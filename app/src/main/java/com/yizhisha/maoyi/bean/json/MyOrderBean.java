package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class MyOrderBean {
    private String status;
    private String info;
    private List<MyOrderListBean> order;
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setOrder(List<MyOrderListBean> order) {
        this.order = order;
    }
    public List<MyOrderListBean> getOrder() {
        return order;
    }

}
