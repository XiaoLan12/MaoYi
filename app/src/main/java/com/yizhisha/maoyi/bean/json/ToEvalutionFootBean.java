package com.yizhisha.maoyi.bean.json;

import java.io.Serializable;

/**
 * Created by lan on 2017/7/6.
 */

public class ToEvalutionFootBean implements Serializable{
    private String orderno;
    private long addtime;
    private String detail;
    private int orderId;
    private int commentstatus;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCommentstatus() {
        return commentstatus;
    }

    public void setCommentstatus(int commentstatus) {
        this.commentstatus = commentstatus;
    }
}
