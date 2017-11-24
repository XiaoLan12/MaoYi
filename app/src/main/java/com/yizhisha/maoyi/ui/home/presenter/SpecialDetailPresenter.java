package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailPresenter extends SpecialDetailContract.Presenter{

    @Override
    public void getSpecialDetail(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getSpecialGoodsList(map),new RxSubscriber<SpecialDetailBean>(mContext,true){
            @Override
            protected void onSuccess(SpecialDetailBean model) {
                mView.getSpecialDetailSuccess(model);
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
