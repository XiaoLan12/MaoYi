package com.yizhisha.maoyi.ui.me.presenter;
import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.MyFootprintContract;

import java.util.Map;
/**
 * Created by lan on 2017/8/15.
 */

public class MyFootprintPresenter extends MyFootprintContract.Presenter{
    @Override
    public void loadFootPrintr(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadFootprint(param),
                new RxSubscriber<FootpringBean>(mContext, false) {
                    @Override
                    protected void onSuccess(FootpringBean bean) {
                        mView.hideLoading();
                        if(bean.getStatus().equals("y")&&bean.getHistory().size()>0){
                            mView.loadSuccess(bean.getHistory());
                        }else{
                            mView.showEmpty();
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
    public void clearFootPrint(Map<String, String> param) {
        addSubscrebe(Api.getInstance().clearFootPrint(param),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean bean) {
                       if(bean!=null&&bean.getStatus().equals("y")){
                           mView.clearFootPrint(bean.getInfo());
                       }else{
                           mView.loadFail(0,bean.getInfo());
                       }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(0,message);
                    }
                });
    }
}
