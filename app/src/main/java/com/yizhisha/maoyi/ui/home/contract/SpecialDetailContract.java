package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */

public interface SpecialDetailContract {
    interface View extends BaseView {

        void getSpecialDetailSuccess(SpecialDetailBean model);
        void getSortedListSuccess(List<SortedListBean.SortedsBean> model);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<SpecialDetailContract.View> {

        public abstract void getSpecialDetail(Map<String,String> map,boolean isShowLoad);
        public abstract void getSortedList();

    }
}
