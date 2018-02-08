package com.yizhisha.maoyi.ui.home.presenter;


import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.MyCommentBean;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.ui.home.contract.CommentYarnContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnPresenter extends CommentYarnContract.Presenter{
    @Override
    public void loadCommentList(int uid,boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadMyComment(uid),new RxSubscriber<MyCommentBean>(mContext,false){
            @Override
            protected void onSuccess(MyCommentBean data) {
                mView.hideLoading();
                if(data.getStatus().equals("y")&&data.getCommentList().size()>0){
                    mView.loadCommentListSuccess(data.getCommentList());
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
