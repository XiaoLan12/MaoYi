package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RefundDetailBean;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.ReFundOrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class ReFundOrderDetailsPresenter extends ReFundOrderDetailsContract.Presenter {
    @Override
    public void loadRefundDetail(Map<String, String> params) {
        addSubscrebe(Api.getInstance().loadRefundDetail(params),new RxSubscriber<RefundDetailBean>(mContext,true){
            @Override
            protected void onSuccess(RefundDetailBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.loadRefundDetailSuccess(info);
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
    public void refundDel(Map<String, String> params) {
        addSubscrebe(Api.getInstance().refundDel(params),new RxSubscriber<RequestStatusBean>(mContext,true){
            @Override
            protected void onSuccess(RequestStatusBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.refundDel(info.getInfo());
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
