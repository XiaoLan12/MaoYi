package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyOrderBean;
import com.yizhisha.maoyi.bean.json.TuiguangItem1Bean;
import com.yizhisha.maoyi.bean.json.TuiguangItem2Bean;
import com.yizhisha.maoyi.ui.me.contract.TuiguangItem1Contract;

import java.util.Map;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem1Presenter extends TuiguangItem1Contract.Presenter{
    @Override
    public void loadTuiGuangItem1(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().tuiguangItem1(param),
                new RxSubscriber<TuiguangItem1Bean>(mContext, false) {
                    @Override
                    protected void onSuccess(TuiguangItem1Bean bean) {
                        mView.hideLoading();
                        if(bean.getStatus().equals("y")){
                            if(bean.getInfo().size()>0){
                                mView.loadTuiGuangItem1Success(bean.getInfo());
                            }else{
                               mView.showEmpty();
                            }
                        }else{
                            mView.loadFail(1,"加载失败");
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
    public void loadTuiGuangItem1LoreMore(Map<String, String> param) {
        addSubscrebe(Api.getInstance().tuiguangItem1(param),
                new RxSubscriber<TuiguangItem1Bean>(mContext, false) {
                    @Override
                    protected void onSuccess(TuiguangItem1Bean bean) {
                       mView.loadTuiGuangItem1LoreMore(bean.getInfo());
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadMoreFail();
                    }
                });
    }

    @Override
    public void loadTuiGuangItem2(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().tuiguangItem2(param),
                new RxSubscriber<TuiguangItem2Bean>(mContext, false) {
                    @Override
                    protected void onSuccess(TuiguangItem2Bean bean) {
                        mView.hideLoading();
                        if(bean.getStatus().equals("y")){
                            if(bean.getInfo().size()>0){
                                mView.loadTuiGuangItem2Success(bean.getInfo());
                            }else{
                                mView.showEmpty();
                            }
                        }else{
                            mView.loadFail(1,"加载失败");
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
    public void loadTuiGuangItem2LoreMore(Map<String, String> param) {
        addSubscrebe(Api.getInstance().tuiguangItem2(param),
                new RxSubscriber<TuiguangItem2Bean>(mContext, false) {
                    @Override
                    protected void onSuccess(TuiguangItem2Bean bean) {
                        mView.loadTuiGuangItem2LoreMore(bean.getInfo());
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadMoreFail();
                    }
                });
    }
}
