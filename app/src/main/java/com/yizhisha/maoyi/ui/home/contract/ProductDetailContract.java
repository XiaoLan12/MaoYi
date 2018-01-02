package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/25.
 */

public interface ProductDetailContract {
    interface View extends BaseView {

        void getGoodsDetailSuccess(GoodsDetailBean model);
        void getSimilarRecommenSuccess(SimilarRecommenBean model);
        void addShoppCartSuccess(String result);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<ProductDetailContract.View> {

        public abstract void getGoodsDetail(Map<String,String> map);
        public abstract void addShoppCart(Map<String, String> map);
        public abstract void getSimilarRecommen();
    }
}
