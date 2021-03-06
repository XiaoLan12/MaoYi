package com.yizhisha.maoyi.bean.json;

import java.io.Serializable;

/**
 * Created by lan on 2017/7/6.
 */

public class OrderFootBean implements Serializable{
    private int id;
    private double totalprice;
    private String orderno;
    private int status;
    private int amount;
    private int type;
    private String clinkman;
    private int commentstatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCommentstatus() {
        return commentstatus;
    }

    public void setCommentstatus(int commentstatus) {
        this.commentstatus = commentstatus;
    }


    public double getTotalprice() {
        return totalprice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getClinkman() {
        return clinkman;
    }

    public void setClinkman(String clinkman) {
        this.clinkman = clinkman;
    }


    @Override
    public String toString() {
        return "OrderFootBean{" +
                "totalprice=" + totalprice +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
