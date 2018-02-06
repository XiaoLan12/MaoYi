package com.yizhisha.maoyi.ui.me.contract;


import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;

import java.util.Map;

/**
 * Created by lan on 2017/8/2.
 */

public interface BindContract {
    interface View extends BaseView {
        void bindSuccess(RequestStatusBean msg);
        void getCodeSuccess(String info);
        void loadBindPhone(String msg);
        void loadBindPhoneFail(String msg);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void bing(Map<String, String> params);
        public abstract void getCode(Map<String, String> map);
        public abstract void loadBindPhone(int uid);

    }
}
