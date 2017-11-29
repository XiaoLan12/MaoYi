package com.yizhisha.maoyi.ui.me.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyCommentBean;
import com.yizhisha.maoyi.ui.me.contract.HaveEvaluationContract;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class HaveEvaluatioPresenter extends HaveEvaluationContract.Presenter{
    @Override
    public void loadComment(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadMyComment(uid),
                new RxSubscriber<MyCommentBean>(mContext, false) {
                    @Override
                    protected void onSuccess(MyCommentBean info) {
                        mView.hideLoading();
                        if(info.getStatus().equals("y")&&info.getCommentList().size()>0){
                            mView.loadCommentSuccess(info.getCommentList());
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
