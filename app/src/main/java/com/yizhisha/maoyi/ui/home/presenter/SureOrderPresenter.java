package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopCartOrderSureBean;
import com.yizhisha.maoyi.ui.home.contract.SureOrderContract;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class SureOrderPresenter extends SureOrderContract.Presenter{
    @Override
    public void loadOrderSure(Map<String, String> param) {
        addSubscrebe(Api.getInstance().orderSure(param), new RxSubscriber<OrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(OrderSureBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.loadOrderSuccess(bean);
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void loadShopCartOrderSure(Map<String, String> param) {
        addSubscrebe(Api.getInstance().shopCartOrderSure(param), new RxSubscriber<ShopCartOrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(ShopCartOrderSureBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.loadShopCartOrderSuccess(bean);
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void createOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().createOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                mView.hideLoading();
                if(bean.getStatus().equals("y")){
                    mView.createOrderSuccess(bean);
                }else{
                    mView.hideLoading();
                    mView.loadFail(bean.getInfo());
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
    public void createShopCartOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().createShopCartOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                mView.hideLoading();
                if(bean.getStatus().equals("y")){
                    mView.createShopCartOrderSuccess(bean);
                }else{
                    mView.hideLoading();
                    mView.loadFail(bean.getInfo());
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
