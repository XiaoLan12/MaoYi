package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface SDayExplosionContract {
    interface View extends BaseView {
        void getWeekToprSuccess(List<WeekTopListBean.WeekTopBean> model);
        void getWeekListtSuccess(List<WeekListBean.WeekBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<SDayExplosionContract.View> {

        public abstract void getWeekTop();
        public abstract void getWeekList();

    }
}
