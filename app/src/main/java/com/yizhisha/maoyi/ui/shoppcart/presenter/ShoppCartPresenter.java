package com.yizhisha.maoyi.ui.shoppcart.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;
import com.yizhisha.maoyi.bean.json.SingleShoppCartBean;
import com.yizhisha.maoyi.bean.json.SingleShoppGoodBean;
import com.yizhisha.maoyi.ui.shoppcart.contract.ShoppCartContract;

import java.util.Map;

import static android.R.id.message;

/**
 * Created by lan on 2017/7/10.
 */

public class ShoppCartPresenter extends ShoppCartContract.Presenter{
    @Override
    public void loadShoppCart(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getShoppCart(uid),
                new RxSubscriber<ShopcartListBean>(mContext, false) {
                    @Override
                    protected void onSuccess(ShopcartListBean bean) {
                        mView.hideLoading();
                        if(bean.getStatus().equals("y")&&bean.getShopcart().size()>0){
                            mView.loadSuccess(bean.getShopcart());
                        }else if(bean.getStatus()!=null&&bean.getStatus().equals("n")){
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
    public void loadSingleShoppCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().loadSingleShpCart(map),
                new RxSubscriber<SingleShoppCartBean>(mContext, false) {
                    @Override
                    protected void onSuccess(SingleShoppCartBean bean) {
                        if(bean!=null){
                            SingleShoppGoodBean good=bean.getGoods();
                            if(good.getStatus().equals("y")){
                                mView.loadSingleSuccess(good);
                            }else{
                                mView.loadFail(0,good.getInfo());
                            }
                        }else{
                            mView.loadFail(0,"数据加载失败");
                        }

                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(0,message);
                    }
                });
    }

    @Override
    public void editShoppCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().changeShopCart(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean bean) {
                            if(bean.getStatus().equals("y")){
                                mView.editShoppCartSuccess(bean.getInfo());
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

    @Override
    public void deleteShoppCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().deleteShoppCart(map),new RxSubscriber<RequestStatusBean>(mContext,true){

            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if("y".equals(requestStatusBean.getStatus())){
                    mView.deleteShoppCart(requestStatusBean.getInfo());
                }else{
                    mView.loadFail(0,requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(0,message);
            }
        });
    }

}
