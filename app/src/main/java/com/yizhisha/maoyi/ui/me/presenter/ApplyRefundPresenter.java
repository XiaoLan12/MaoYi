package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.CommentPicBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.ApplyRefundContract;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class ApplyRefundPresenter extends ApplyRefundContract.Presenter{
    @Override
    public void addRefund(Map<String, String> map) {
        addSubscrebe(Api.getInstance().refundApply(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        mView.hideLoading();
                        if(requestStatusBean!=null&&requestStatusBean.getStatus().equals("y")){
                            mView.addRefundSuccess(requestStatusBean.getInfo());
                        }else{
                            mView.hideLoading();
                            mView.loadFail(requestStatusBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void addPic(MultipartBody.Part body) {
        addSubscrebe(Api.getInstance().uploadRefundPic(body),
                new RxSubscriber<CommentPicBean>(mContext, false) {
                    @Override
                    protected void onSuccess(CommentPicBean commentPicBean) {
                        if(commentPicBean!=null&&!commentPicBean.getRefundPic().equals("")){
                            mView.addPicSuccess(commentPicBean.getRefundPic());
                        }else{
                            mView.hideLoading();
                            mView.loadFail("发布失败");
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }
}
