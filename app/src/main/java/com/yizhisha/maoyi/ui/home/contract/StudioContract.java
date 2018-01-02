package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface StudioContract {
    interface View extends BaseView {

        void getStudioSuccess(List<StudioBean.StudioListBean> model);
        void showLoading();
        void hideLoading();

        void showEmpty();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<StudioContract.View> {

        public abstract void getStudio(boolean isShowLoad);

    }
}
