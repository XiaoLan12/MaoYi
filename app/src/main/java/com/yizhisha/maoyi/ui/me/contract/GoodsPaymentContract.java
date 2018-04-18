package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopCartOrderSureBean;
import com.yizhisha.maoyi.ui.home.contract.SureOrderContract;

import java.util.List;
import java.util.Map;

/**
 * Created by 小蓝 on 2018/4/18.
 */

public interface GoodsPaymentContract {
    interface View extends BaseView {
        void loadoSuccess(List<MyOrderListBean> data);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadOrderDetails(Map<String, String> param, boolean isShowLoad);
    }
}
