package com.yizhisha.maoyi.ui.classify.presenter;

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
        addSubscrebe(Api.getInstance().getSorted(),new RxSubscriber<List<SortedListBean>>(mContext,true){

            @Override
            protected void onSuccess(List<SortedListBean> model) {
                List<SortedListBean> model1=model;
//                model1.add(model.get(0));
//                model1.add(model.get(0));

                mView.getSortedListSuccess(model1);

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
