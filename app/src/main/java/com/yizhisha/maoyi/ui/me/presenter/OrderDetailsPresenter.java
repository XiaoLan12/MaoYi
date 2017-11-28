package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyOrderBean;
import com.yizhisha.maoyi.ui.me.contract.OrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class OrderDetailsPresenter extends OrderDetailsContract.Presenter{
    @Override
    public void loadOrderDetails(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadOrderDetail(param), new RxSubscriber<MyOrderBean>(mContext,false) {
            @Override
            protected void onSuccess(MyOrderBean myOrderListBean) {
                mView.hideLoading();
                if(myOrderListBean.getStatus().equals("y")&&myOrderListBean.getOrder().size()>0){
                    mView.loadoSuccess(myOrderListBean.getOrder());
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
    public void changePayWay(Map<String, String> param) {

    }

    @Override
    public void sureGoods(Map<String, String> param) {

    }

    @Override
    public void cancleOrder(Map<String, String> param) {

    }

    @Override
    public void weChatPay(Map<String, String> param) {

    }

    @Override
    public void loadWeChatPayState(Map<String, String> param) {

    }
}
