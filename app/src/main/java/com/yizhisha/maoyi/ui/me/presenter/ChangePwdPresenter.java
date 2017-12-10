package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.ChangePwdContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class ChangePwdPresenter extends ChangePwdContract.Presenter{
    @Override
    public void changePwd(Map<String, String> param) {
        addSubscrebe(Api.getInstance().changePwd(param),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if("y".equals(requestStatusBean.getStatus())){
                            mView.changePwdSuccess(requestStatusBean.getInfo());
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
