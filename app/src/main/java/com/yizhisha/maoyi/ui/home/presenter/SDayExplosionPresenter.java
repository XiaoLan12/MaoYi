package com.yizhisha.maoyi.ui.home.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;
import com.yizhisha.maoyi.ui.home.contract.SDayExplosionContract;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SDayExplosionPresenter extends SDayExplosionContract.Presenter{
    @Override
    public void getSortedList() {
        addSubscrebe(Api.getInstance().getSorted(),new RxSubscriber<SortedListBean>(mContext,false){

            @Override
            protected void onSuccess(SortedListBean model) {

                mView.getSortedListSuccess(model.getList());

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
    @Override
    public void getWeekTop() {
        addSubscrebe(Api.getInstance().getWeekTop(),new RxSubscriber<WeekTopListBean>(mContext,false){
            @Override
            protected void onSuccess(WeekTopListBean model) {
                mView.getWeekToprSuccess(model.getList());
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getWeekList(Map<String,String> map) {
        addSubscrebe(Api.getInstance().getWeekList(map),new RxSubscriber<WeekListBean>(mContext,false){

            @Override
            protected void onSuccess(WeekListBean model) {
                mView.getWeekListtSuccess(model.getList());
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
