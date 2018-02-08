package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SimilarRecommenListBean {
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    private List<WeekListBean.WeekBean> goods;

    public List<WeekListBean.WeekBean> getGoods() {
        return goods;
    }


}
