package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public interface OrderTrackContract {
    interface View extends BaseView {
        //void loadExpressSuccess(RefundDetailBean data);
        void loadRefundExpressSuccess(RefundExpressBean data);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadExpressDetail(String orderno);
        public abstract void loadRefundExpressDetail(String refunndno);

    }
}
