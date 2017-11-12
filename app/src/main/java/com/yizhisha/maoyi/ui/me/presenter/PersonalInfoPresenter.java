package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.PersonalInfoContract;

import java.util.Map;

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
}
