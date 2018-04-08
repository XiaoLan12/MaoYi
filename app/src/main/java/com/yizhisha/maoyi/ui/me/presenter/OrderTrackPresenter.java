package com.yizhisha.maoyi.ui.me.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RefundExpressBean;
import com.yizhisha.maoyi.ui.me.contract.OrderTrackContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class OrderTrackPresenter extends OrderTrackContract.Presenter {
    @Override
    public void loadExpressDetail(String orderno) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().loadExpress(orderno),new RxSubscriber<ResponseBody>(mContext,true){
            @Override
            protected void onSuccess(ResponseBody info) {
                try {
                    mView.hideLoading();
                        mView.loadRefundExpressSuccess(info.string());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void loadRefundExpressDetail(String refunndno) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().loadRefundExpress(refunndno),new RxSubscriber<ResponseBody>(mContext,true){
            @Override
            protected void onSuccess(ResponseBody info) {
                try {
                    mView.hideLoading();
                    mView.loadRefundExpressSuccess(info.string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
            }
        });
    }
}
