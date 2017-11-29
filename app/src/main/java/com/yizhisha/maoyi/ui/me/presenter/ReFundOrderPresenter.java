package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyCommentBean;
import com.yizhisha.maoyi.bean.json.RefundBean;
import com.yizhisha.maoyi.ui.me.contract.ReFundOrderContract;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class ReFundOrderPresenter extends ReFundOrderContract.Presenter{
    @Override
    public void loadRefund(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadRefundList(uid),
                new RxSubscriber<RefundBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RefundBean info) {
                        mView.hideLoading();
                        if(info.getStatus().equals("y")&&info.getRefund().size()>0){
                            mView.loadRefundSuccess(info.getRefund());
                        }else{
                            mView.showEmpty();
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
