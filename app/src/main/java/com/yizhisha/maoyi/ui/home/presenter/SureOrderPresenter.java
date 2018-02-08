package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
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
                }else if(bean.getStatus().equals("n")&&bean.getInfo().equals("请先添加收货地址")||bean.getInfo().equals("请先添加收货地址。")){
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
        addSubscrebe(Api.getInstance().shopCartOrderSure(param), new RxSubscriber<OrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(OrderSureBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.loadShopCartOrderSuccess(bean);
                }else if(bean.getStatus().equals("n")&&bean.getInfo().equals("请先添加收货地址")||bean.getInfo().equals("请先添加收货地址。")){
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
}
