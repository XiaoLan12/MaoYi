package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public interface PersonalInfoContract {
    interface View extends BaseView {
        void changeSuccess(String result);
        void loadFail(String msg);
    }



    abstract class Presenter extends BasePresenter<View>{
        public abstract void changePersonalInfo(Map<String, String> params);
    }
}
