package com.yizhisha.maoyi.ui.classify.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;

/**
 * Created by Administrator on 2017/11/22.
 */

public class ClassifyContract {
    interface View extends BaseView {

        void getPastListSuccess(ListBean<DailyBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<ClassifyContract.View> {

        public abstract void getPastList();

    }
}
