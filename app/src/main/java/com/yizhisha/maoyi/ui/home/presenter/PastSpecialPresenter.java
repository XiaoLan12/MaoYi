package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.ui.home.contract.PastSpecialContract;

/**
 * Created by Administrator on 2017/11/21.
 */

public class PastSpecialPresenter extends PastSpecialContract.Presenter {
    @Override
    public void getPastList(boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getPastList(),new RxSubscriber<ListBean<DailyBean>>(mContext,false){

            @Override
            protected void onSuccess(ListBean<DailyBean> model) {
                mView.hideLoading();
                mView.getPastListSuccess(model);

            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
            }
        });
    }
}
