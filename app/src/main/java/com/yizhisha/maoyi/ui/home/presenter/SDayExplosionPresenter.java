package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopBean;
import com.yizhisha.maoyi.ui.home.contract.SDayExplosionContract;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SDayExplosionPresenter extends SDayExplosionContract.Presenter{
    @Override
    public void getWeekTop() {
        addSubscrebe(Api.getInstance().getWeekTop(),new RxSubscriber<List<WeekTopBean>>(mContext,true){
            @Override
            protected void onSuccess(List<WeekTopBean> model) {
                mView.getWeekToprSuccess(model);
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getWeekList() {
        addSubscrebe(Api.getInstance().getWeekList(),new RxSubscriber<List<WeekListBean>>(mContext,true){

            @Override
            protected void onSuccess(List<WeekListBean> model) {
                mView.getWeekListtSuccess(model);

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
