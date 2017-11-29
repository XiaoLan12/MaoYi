package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.RefundListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public interface ReFundOrderContract {
    interface View extends BaseView {
        void loadRefundSuccess(List<RefundListBean> data);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(String msg);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadRefund(int uid, boolean isShowLoad);

    }
}
