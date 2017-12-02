package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public interface OrderSearchContract {
    interface View extends BaseView {
        void loadOrderSuccess(List<MyOrderListBean> data);
        void sureGoodsSuuccess(String msg);
        void cancleOrder(String msg);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(int code,String msg);
    }
    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadOrder(Map<String, String> param, boolean isShowLoad);
        public abstract void cancleOrder(Map<String, String> param);
        public abstract void sureGoods(Map<String, String> param);

    }
}
