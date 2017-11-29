package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.UserHeadBean;
import com.yizhisha.maoyi.ui.me.contract.PersonalInfoContract;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class PersonalInfoPresenter extends PersonalInfoContract.Presenter {
    @Override
    public void changePersonalInfo(Map<String, String> params) {
        addSubscrebe(Api.getInstance().changeUserInfo(params),new RxSubscriber<RequestStatusBean>(mContext,true){
            @Override
            protected void onSuccess(RequestStatusBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.changeSuccess(info.getInfo());
                    }else{
                        mView.loadFail(info.getInfo());
                    }
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void changeHeadSuccess(RequestBody uid, MultipartBody.Part body) {
        addSubscrebe(Api.getInstance().changeUserHead(uid,body),new RxSubscriber<UserHeadBean>(mContext,true){
            @Override
            protected void onSuccess(UserHeadBean info) {
                if(info.getStatus().equals("y")) {
                    mView.changeHeadSuccess(info);
                } else{
                    mView.loadFail(info.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void loadHeadInfo(int uid) {
        addSubscrebe(Api.getInstance().loadHeadInfo(uid),new RxSubscriber<MeInfoBean>(mContext,true){

            @Override
            protected void onSuccess(MeInfoBean meInfoBean) {
                if(meInfoBean.getStatus().equals("y")){
                    mView.loadHeadSuccess(meInfoBean);
                }else{
                    mView.loadFail(meInfoBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
