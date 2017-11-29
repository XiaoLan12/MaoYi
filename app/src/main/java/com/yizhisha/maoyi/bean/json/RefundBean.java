package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class RefundBean {
    private String status;
    private String info;
    private List<RefundListBean> refund;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<RefundListBean> getRefund() {
        return refund;
    }

    public void setRefund(List<RefundListBean> refund) {
        this.refund = refund;
    }
}
