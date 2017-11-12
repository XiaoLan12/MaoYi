package com.yizhisha.maoyi.ui.me.contract;


import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.GoodsListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public interface MyAddressContract {
    interface View extends BaseView {
        void loadAddressSuccess(List<GoodsListBean.Address> data);

        void setDefaulsAddressSuccess(String msg);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(int code,String msg);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadAddress(int uid,boolean isShowLoad);
        public abstract void setDefaulsAddress(Map<String,String> map);

    }
}
