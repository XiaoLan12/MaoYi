package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/5.
 */

public class OrderListBean {
    private List<OrderBean> order ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    public void setOrder(List<OrderBean> order){
        this.order = order;
    }
    public List<OrderBean> getOrder(){
        return this.order;
    }

}
