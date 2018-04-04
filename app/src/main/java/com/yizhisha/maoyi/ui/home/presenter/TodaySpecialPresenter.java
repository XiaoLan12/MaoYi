package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;
import com.yizhisha.maoyi.ui.home.contract.TodaySpecialContract;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class TodaySpecialPresenter extends TodaySpecialContract.Presenter{
    @Override
    public void getDailyTopSlider() {
        addSubscrebe(Api.getInstance().getDailyTopSlider(),new RxSubscriber<WeekTopListBean>(mContext,true){

            @Override
            protected void onSuccess(WeekTopListBean model) {
                mView.getDailyTopSliderSuccess(model.getList());

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getDailyList(boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getDailyList(),new RxSubscriber<ListBean<DailyBean>>(mContext,true){

            @Override
            protected void onSuccess(ListBean<DailyBean> model) {
                mView.hideLoading();
                mView.getDailyListSuccess(model);

            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
            }
        });
    }
}
