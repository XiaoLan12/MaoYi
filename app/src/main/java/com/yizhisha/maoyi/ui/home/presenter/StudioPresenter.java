package com.yizhisha.maoyi.ui.home.presenter;

import com.yizhisha.maoyi.api.Api;
import com.yizhisha.maoyi.base.rx.RxSubscriber;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.ui.home.contract.StudioContract;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioPresenter extends StudioContract.Presenter{
    @Override
    public void getStudio(boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getStudio(), new RxSubscriber<StudioBean>(mContext,false) {
            @Override
            protected void onSuccess(StudioBean data) {
                mView.hideLoading();
                if(data.getStatus().equals("y")&&data.getWorkshop().size()>0){
                    mView.getStudioSuccess(data.getWorkshop());
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
