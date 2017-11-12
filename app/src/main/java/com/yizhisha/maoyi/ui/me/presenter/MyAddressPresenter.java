package com.yizhisha.maoyi.ui.me.presenter;
import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.MyAddressContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public class MyAddressPresenter extends MyAddressContract.Presenter{
    @Override
    public void loadAddress(int uid,boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadGoodsAddress(uid), new RxSubscriber<GoodsListBean>(mContext,false) {
            @Override
            protected void onSuccess(GoodsListBean data) {
                mView.hideLoading();
                if(data.getStatus().equals("y")&&data.getAddress().size()>0){
                    mView.loadAddressSuccess(data.getAddress());
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
    public void setDefaulsAddress(Map<String, String> map) {
        addSubscrebe(Api.getInstance().setDefaulsAddress(map),
                new RxSubscriber<RequestStatusBean>(mContext,true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.setDefaulsAddressSuccess(requestStatusBean.getInfo());
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
