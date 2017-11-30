package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.ui.me.contract.OrderTrackContract;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class OrderTrackPresenter extends OrderTrackContract.Presenter {
    @Override
    public void loadExpressDetail(String orderno) {
        addSubscrebe(Api.getInstance().loadExpress(orderno),new RxSubscriber<RefundExpressBean>(mContext,true){
            @Override
            protected void onSuccess(RefundExpressBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.loadRefundExpressSuccess(info);
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
    public void loadRefundExpressDetail(String refunndno) {
        addSubscrebe(Api.getInstance().loadRefundExpress(refunndno),new RxSubscriber<RefundExpressBean>(mContext,true){
            @Override
            protected void onSuccess(RefundExpressBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.loadRefundExpressSuccess(info);
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
