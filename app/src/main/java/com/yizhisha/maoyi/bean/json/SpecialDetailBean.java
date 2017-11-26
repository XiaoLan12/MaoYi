package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailBean {
    private List<DailyBean> special;
    private String status;
    private List<WeekListBean> goods;

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

    public List<WeekListBean> getGoods() {
        return goods;
    }

    public void setGoods(List<WeekListBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SpecialDetailBean{" +
                "special=" + special +
                ", status='" + status + '\'' +
                ", goods=" + goods +
                '}';
    }
}
