package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface PastSpecialContract {
    interface View extends BaseView {

        void getPastListSuccess(ListBean<DailyBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<PastSpecialContract.View> {

        public abstract void getPastList();

    }
}
