package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenListBean;
import com.yizhisha.maoyi.bean.json.WechatBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/25.
 */

public interface ProductDetailContract {
    interface View extends BaseView {

        void getGoodsDetailSuccess(GoodsDetailBean model);
        void getSimilarRecommenSuccess(List<WeekListBean.WeekBean> model);
        void addShoppCartSuccess(String result);
        void collectProductSuccess(String msg);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<ProductDetailContract.View> {

        public abstract void getGoodsDetail(Map<String,String> map);
        public abstract void addShoppCart(Map<String, String> map);
        public abstract void getSimilarRecommen(int tid);
        public abstract void collectProduct(Map<String, String> map);
    }
}
