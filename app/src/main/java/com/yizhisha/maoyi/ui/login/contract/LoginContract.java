package com.yizhisha.maoyi.ui.login.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/9.
 */

public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess(LoginBean info);

        //        void weChatLoginSuccess(RequestStatusBean bean);
//
//
//        void registerSuccess(RequestStatusBean info);
        //
//        void findPwdSuccess(String info);
//
        void getCodeSuccess(RequestStatusBean info);
        //
//        void loadWeChatData(WechatBean wechatBean);
//
        void loadFail(String msg);
//        void weChatLogin(String info);
//
//        void bindWeChatSuccess(String info);
//        void loadWeChatInfo(WechatInfoBean bean);

        void showLoading();
        void hideLoading();

    }

    abstract class Presenter extends BasePresenter<LoginContract.View> {

                public abstract void login(Map<String, String> map);
//        public abstract void register(Map<String, String> map);
        //        public abstract void findPwd(Map<String, String> map);
        public abstract void getCode(Map<String, String> map);
//        public abstract void loadWeChatData(String url);
//        public abstract void weChatLogin(Map<String,String> map);
//        public abstract void bindWeChat(Map<String,String> map);
//        public abstract void loadWeChatInfo(String url);


    }
}
