package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.ui.home.contract.StudioShopContract;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class StudioShopPresenter extends StudioShopContract.Presenter{
    @Override
    public void focusWork(Map<String, String> map) {
        addSubscrebe(Api.getInstance().focusWork(map), new RxSubscriber<RequestStatusBean>(mContext,false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.focusWorkSuccess(bean.getInfo());
                }else{
                    mView.loadFail(0,bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(0,message);
            }
        });
    }
    @Override
    public void getStudioShop(Map<String, String> map, boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getStudioShop(map), new RxSubscriber<StudioShopBean>(mContext,false) {
            @Override
            protected void onSuccess(StudioShopBean data) {
                mView.hideLoading();

                    mView.getStudioShopSuccess(data);

            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(1,message);
            }
        });
    }

    @Override
    public void getSortedList() {
        addSubscrebe(Api.getInstance().getSorted(),new RxSubscriber<SortedListBean>(mContext,false){
            @Override
            protected void onSuccess(SortedListBean model) {
                mView.getSortedListSuccess(model.getList());

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(0,message);
            }
        });
    }
}
