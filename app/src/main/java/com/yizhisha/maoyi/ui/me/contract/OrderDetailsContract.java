package com.yizhisha.maoyi.ui.me.contract;
import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.PayReqBean;
import com.yizhisha.maoyi.bean.json.WeChatPayStateBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public interface OrderDetailsContract {
    interface View extends BaseView {
        void loadoSuccess(List<MyOrderListBean> data);
        void changePayWaySuccess(String info);
        void sureGoodsSuuccess(String msg);
        void cancleOrder(String msg);

        void weChatPay(PayReqBean bean);
        void loadWeChatPayState(WeChatPayStateBean bean);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(int code,String msg);
        void loadWeChatPayStateFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrderDetails(Map<String, String> param, boolean isShowLoad);
        public abstract void changePayWay(Map<String, String> param);
        public abstract void sureGoods(Map<String, String> param);
        public abstract void cancleOrder(Map<String, String> param);
        public abstract void weChatPay(Map<String, String> param);
        public abstract void loadWeChatPayState(Map<String, String> param);

    }
}
