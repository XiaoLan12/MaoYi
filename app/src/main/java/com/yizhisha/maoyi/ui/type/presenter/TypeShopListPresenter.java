package com.yizhisha.maoyi.ui.type.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.ui.type.contract.TypeShopListContract;

import java.util.Map;

/**
 * Created by 小蓝 on 2018/3/24.
 */

public class TypeShopListPresenter extends TypeShopListContract.Presenter{
    @Override
    public void getGoodsSorted(Map<String, String> map, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().goodsSorted(map),new RxSubscriber<SpecialDetailBean>(mContext,true){
            @Override
            protected void onSuccess(SpecialDetailBean model) {
                mView.hideLoading();
                if(model.getStatus().equals("y")) {
                    if (model.getGoods().size()==0){
                        mView.showEmpty();
                    }else {
                        mView.getGoodsSortedSuccess(model);
                    }
                }else{
                    mView.loadFail(1,model.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(1,"");
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
                if(data.getStatus().equals("y")) {
                    if (data.getGoods().size()==0){
                        mView.showEmpty();
                    }else {
                        mView.getStudioShopSuccess(data);
                    }
                }else{
                    mView.loadFail(1,data.getInfo());
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
