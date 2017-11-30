package com.yizhisha.maoyi.bean.json;

import java.io.Serializable;

/**
 * Created by lan on 2017/7/6.
 */

public class RefundFootBean implements Serializable{
    private int refundstatus;
    private String orderno;
    private String refundno;
    private int type;

    public String getRefundno() {
        return refundno;
    }

    public void setRefundno(String refundno) {
        this.refundno = refundno;
    }

    public int getRefundstatus() {
        return refundstatus;
    }

    public void setRefundstatus(int refundstatus) {
        this.refundstatus = refundstatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}
