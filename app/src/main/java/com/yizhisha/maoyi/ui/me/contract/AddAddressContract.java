package com.yizhisha.maoyi.ui.me.contract;


import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public interface AddAddressContract {
    interface View extends BaseView {
        void addAddressSuccess(RequestStatusBean data);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void addAddress(Map<String,String> map);
    }
}
