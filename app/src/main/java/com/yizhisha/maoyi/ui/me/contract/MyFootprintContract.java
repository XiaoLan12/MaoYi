package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.FootpringBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/15.
 */

public interface MyFootprintContract {
    interface View extends BaseView {
        void loadSuccess(List<FootpringBean.History> data);
        void clearFootPrint(String msg);
        void showLoading();

        void hideLoading();

        void showEmpty();
        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadFootPrintr(Map<String, String> param, boolean isShowLoad);
        public abstract void clearFootPrint(Map<String, String> param);

    }
}
