package com.yizhisha.maoyi.ui.type.presenter;

import android.util.Log;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.ui.type.contract.TypeContract;

import java.util.Map;

/**
 * Created by 小蓝 on 2018/3/21.
 */

public class TypePresenter extends TypeContract.Presenter{

    @Override
    public void getSortedList() {
        addSubscrebe(Api.getInstance().getSorted(),new RxSubscriber<SortedListBean>(mContext,false){

            @Override
            protected void onSuccess(SortedListBean model) {
                Log.e("UUU","jinlail");
                mView.getSortedListSuccess(model.getList());

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getStudio() {
        addSubscrebe(Api.getInstance().getStudio(), new RxSubscriber<StudioBean>(mContext,false) {
            @Override
            protected void onSuccess(StudioBean data) {
                if(data.getStatus().equals("y")&&data.getWorkshop().size()>0){
                    mView.getStudioSuccess(data.getWorkshop());
                }else{
                    mView.loadFail(data.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
