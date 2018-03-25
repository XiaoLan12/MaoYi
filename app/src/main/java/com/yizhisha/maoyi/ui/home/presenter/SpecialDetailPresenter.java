package com.yizhisha.maoyi.ui.home.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailPresenter extends SpecialDetailContract.Presenter{

    @Override
    public void getSpecialDetail(Map<String, String> map,boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getSpecialGoodsList(map),new RxSubscriber<SpecialDetailBean>(mContext,true){
            @Override
            protected void onSuccess(SpecialDetailBean model) {
                mView.hideLoading();
                if(model.getStatus().equals("y")) {
                    if (model.getGoods().size()==0){
                        mView.showEmpty();
                    }else {
                        mView.getSpecialDetailSuccess(model);
                    }
                }else{
                    mView.loadFail(1,model.getStatus());
                }
            }
            @Override
            protected void onFailure(String message) {
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
