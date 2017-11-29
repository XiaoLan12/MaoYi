package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.UserHeadBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public interface PersonalInfoContract {
    interface View extends BaseView {
        void changeSuccess(String result);
        void changeHeadSuccess(UserHeadBean msg);
        void loadHeadSuccess(MeInfoBean info);
        void loadFail(String msg);
    }



    abstract class Presenter extends BasePresenter<View>{
        public abstract void changePersonalInfo(Map<String, String> params);
        public abstract void changeHeadSuccess(RequestBody uid, MultipartBody.Part body);
        public abstract void loadHeadInfo(int uid);
    }
}
