package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyOrderBean;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.ui.me.contract.MyOrderContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class MyOrderPresenter extends MyOrderContract.Presenter{

    @Override
    public void loadOrder(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadOrderList(param),
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

    }

    @Override
    public void sureGoods(Map<String, String> param) {

    }
}
