package com.yizhisha.maoyi.ui.classify.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.ui.classify.contract.ClassifyContract;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ClassifyPresenter extends ClassifyContract.Presenter{

    @Override
    public void getSortedList() {
        addSubscrebe(Api.getInstance().getSorted(),new RxSubscriber<SortedListBean>(mContext,true){

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
}
