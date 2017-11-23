package com.yizhisha.maoyi.ui.classify.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public interface ClassifyContract {
    interface View extends BaseView {

        void getSortedListSuccess(List<SortedListBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<ClassifyContract.View> {

        public abstract void getSortedList();

    }
}
