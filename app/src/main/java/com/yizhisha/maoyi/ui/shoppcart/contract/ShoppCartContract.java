package com.yizhisha.maoyi.ui.shoppcart.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.ShopcartBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SingleShoppGoodBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/10.
 */

public interface ShoppCartContract {
    interface View extends BaseView {
        void loadSuccess(List<ShopcartBean> data);
        void loadSingleSuccess(SingleShoppGoodBean data);
        void editShoppCartSuccess(String result);
        void deleteShoppCart(String msg);
        void showLoading();
        void hideLoading();

        void showEmpty();

        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadShoppCart(int uid,boolean isShowLoad);
        public abstract void loadSingleShoppCart(Map<String, String> map);
        public abstract void editShoppCart(Map<String, String> map);
        public abstract void deleteShoppCart(Map<String, String> map);

    }
}
