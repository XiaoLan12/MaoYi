package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.CollectListBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.MyCollectConstract;

import java.util.Map;

/**
 * Created by lan on 2017/7/3.
 */

public class MyCollectPresenter extends MyCollectConstract.Presenter{
    @Override
    public void loadCollect(Map<String, String> param,boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadCollectList(param), new RxSubscriber<CollectListBean>(mContext,false) {
            @Override
            protected void onSuccess(CollectListBean data) {
                mView.hideLoading();
                if(data.getStatus().equals("y")&&data.getData()!=null){
                    mView.loadCollectSuccess(data.getData());
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
    public void cacheCollect(Map<String, String> param) {
        addSubscrebe(Api.getInstance().cacheCollect(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if("y".equals(requestStatusBean.getStatus())){
                    mView.cacheSuccess(requestStatusBean.getInfo());
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
