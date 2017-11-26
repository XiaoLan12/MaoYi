package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SimilarRecommenBean {
    private List<WeekListBean> goods;

    public List<WeekListBean> getGoods() {
        return goods;
    }

    public void setGoods(List<WeekListBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SimilarRecommenBean{" +
                "goods=" + goods +
                '}';
    }
}
