package com.yizhisha.maoyi.ui.me.presenter;

import android.util.Log;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.ui.me.contract.MeContract;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class MePresenter extends MeContract.Presenter{
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
    public void load(){
        addSubscrebe(Api.getInstance().login(),new RxSubscriber<LoginBean>(mContext,true){

            @Override
            protected void onSuccess(LoginBean meInfoBean) {
               loadHeadInfo(AppConstant.UID);
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
