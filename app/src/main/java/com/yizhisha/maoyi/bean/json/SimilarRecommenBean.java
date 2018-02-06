package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SimilarRecommenBean {
    private List<WeekListBean.WeekBean> goods;

    public List<WeekListBean.WeekBean> getGoods() {
        return goods;
    }

    public void setGoods(List<WeekListBean.WeekBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SimilarRecommenBean{" +
                "goods=" + goods +
                '}';
    }
}
