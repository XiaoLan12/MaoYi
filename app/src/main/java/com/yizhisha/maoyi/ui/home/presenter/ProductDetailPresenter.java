package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.GoodsDetailBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.SimilarRecommenListBean;
import com.yizhisha.maoyi.ui.home.contract.ProductDetailContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/25.
 */

public class ProductDetailPresenter extends ProductDetailContract.Presenter {
    @Override
    public void getGoodsDetail(Map<String,String> map) {
        addSubscrebe(Api.getInstance().getGoodsDetail(map),new RxSubscriber<GoodsDetailBean>(mContext,true){

            @Override
            protected void onSuccess(GoodsDetailBean model) {
                mView.getGoodsDetailSuccess(model);
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void addShoppCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().changeShopCart(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean bean) {
                        if(bean.getStatus().equals("y")){
                            mView.addShoppCartSuccess(bean.getInfo());
                        }else{
                            mView.loadFail(bean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void getSimilarRecommen(int tid) {
        addSubscrebe(Api.getInstance().getSimilarRecommen(tid),new RxSubscriber<SimilarRecommenListBean>(mContext,true){

            @Override
            protected void onSuccess(SimilarRecommenListBean model) {
                if(model.getStatus().equals("y")) {
                    mView.getSimilarRecommenSuccess(model.getGoods());
                }else{
                    mView.loadFail(model.getInfo());
                }

            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void collectProduct(Map<String, String> map) {
        addSubscrebe(Api.getInstance().collectProduct(map), new RxSubscriber<RequestStatusBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                    mView.collectProductSuccess(bean.getInfo());
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
