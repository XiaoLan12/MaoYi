package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.OrderSureBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/14.
 */

public interface SureOrderContract {
    interface View extends BaseView {
        void loadOrderSuccess(OrderSureBean data);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadOrderSure(Map<String, String> param);
    }
}
