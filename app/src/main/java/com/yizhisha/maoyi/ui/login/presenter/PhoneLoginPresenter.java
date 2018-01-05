package com.yizhisha.maoyi.ui.login.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.login.contract.PhoneLoginContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/9.
 */

public class PhoneLoginPresenter extends PhoneLoginContract.Presenter {
    @Override
    public void login(Map<String, String> map) {
        addSubscrebe(Api.getInstance().Login(map), new RxSubscriber<LoginBean>(mContext,true) {
            @Override
            protected void onSuccess(LoginBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.loginSuccess(requestStatusBean);
                }else{
                    mView.loadFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getCode(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getCode(map), new RxSubscriber<RequestStatusBean>(mContext,false) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.getCodeSuccess(requestStatusBean);
                }else{
                    mView.loadFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

}
