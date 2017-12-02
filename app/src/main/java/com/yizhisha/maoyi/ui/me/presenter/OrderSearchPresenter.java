package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyOrderBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.OrderSearchContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class OrderSearchPresenter extends OrderSearchContract.Presenter{
    @Override
    public void loadOrder(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().queryOrderList(param),
                new RxSubscriber<MyOrderBean>(mContext, false) {
                    @Override
                    protected void onSuccess(MyOrderBean bean) {
                        mView.hideLoading();
                        if(bean.getStatus().equals("y")&&bean.getOrder().size()>0){
                            mView.loadOrderSuccess(bean.getOrder());
                        }else{
                            mView.showEmpty();
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(1,message);
                    }
                });
    }

    @Override
    public void cancleOrder(Map<String, String> param) {
        addSubscrebe(Api.getInstance().cancelOrder(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.cancleOrder(requestStatusBean.getInfo());
                }else{
                    mView.loadFail(0,requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(0,message);
            }
        });
    }

    @Override
    public void sureGoods(Map<String, String> param) {
        addSubscrebe(Api.getInstance().sureGoods(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.sureGoodsSuuccess(requestStatusBean.getInfo());
                }else{
                    mView.loadFail(0,requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(0,message);
            }
        });
    }
}
