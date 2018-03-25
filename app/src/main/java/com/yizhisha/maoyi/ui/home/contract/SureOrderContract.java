package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopCartOrderSureBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/14.
 */

public interface SureOrderContract {
    interface View extends BaseView {
        void loadOrderSuccess(OrderSureBean data);
        void loadShopCartOrderSuccess(ShopCartOrderSureBean data);
        void createOrderSuccess(RequestStatusBean bean);
        void createShopCartOrderSuccess(RequestStatusBean bean);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadOrderSure(Map<String, String> param);
        public abstract void loadShopCartOrderSure(Map<String, String> param);
        public abstract void createOrder(Map<String, String> param);
        public abstract void createShopCartOrder(Map<String, String> param);
    }
}
