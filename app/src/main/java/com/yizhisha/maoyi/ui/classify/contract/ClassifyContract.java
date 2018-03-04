package com.yizhisha.maoyi.ui.classify.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */

public interface ClassifyContract {
    interface View extends BaseView {
        void getStudioSuccess(List<StudioBean.StudioListBean> model);
        void getSortedListSuccess(List<SortedListBean.SortedsBean> model);
        void loadFail(String msg);
        void showEmpty();
    }

    abstract class Presenter extends BasePresenter<ClassifyContract.View> {

        public abstract void getSortedList();
        public abstract void getStudio(boolean isShowLoad);
    }
}
