package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */

public interface StudioShopContract {
    interface View extends BaseView {

        void getStudioShopSuccess(StudioShopBean model);
        void focusWorkSuccess(String msg);
        void getSortedListSuccess(List<SortedListBean.SortedsBean> model);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<StudioShopContract.View> {
        public abstract void focusWork(Map<String, String> map);
        public abstract void getStudioShop(Map<String,String> map,boolean isShowLoad);
        public abstract void getSortedList();

    }
}
