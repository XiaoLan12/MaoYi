package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.bean.json.RefundListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public interface ReFundOrderDetailsContract {
    interface View extends BaseView {
        void loadRefundDetailSuccess(RefundDetailBean data);
        void refundDel(String data);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadRefundDetail(Map<String, String> params);
        public abstract void refundDel(Map<String, String> params);

    }
}
