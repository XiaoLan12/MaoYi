package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailBean {
    private List<DailyBean> special;
    private String status;
    private String info;
    private List<WeekListBean.WeekBean> goods;

    public List<DailyBean> getSpecial() {
        return special;
    }

    public void setSpecial(List<DailyBean> special) {
        this.special = special;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WeekListBean.WeekBean> getGoods() {
        return goods;
    }

    public void setGoods(List<WeekListBean.WeekBean> goods) {
        this.goods = goods;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SpecialDetailBean{" +
                "special=" + special +
                ", status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", goods=" + goods +
                '}';
    }
}
