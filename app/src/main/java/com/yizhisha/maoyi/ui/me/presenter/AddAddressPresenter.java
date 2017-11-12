package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.AddAddressContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class AddAddressPresenter extends AddAddressContract.Presenter {
    @Override
    public void addAddress(Map<String, String> map) {
        addSubscrebe(Api.getInstance().savaGoodsAddress(map), new RxSubscriber<RequestStatusBean>(mContext,"正在保存...",true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.addAddressSuccess(requestStatusBean);
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
