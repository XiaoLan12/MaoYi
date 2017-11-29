package com.yizhisha.maoyi.ui.shoppcart.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.ShopcartBean;
import com.yizhisha.maoyi.bean.json.ShopcartListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/10.
 */

public interface ShoppCartContract {
    interface View extends BaseView {
        void loadSuccess(List<ShopcartBean> data);
        void deleteShoppCart(String msg);
        void deleteOneShoppCart(String msg, int groupPosition, int childPosition);
        void showLoading();
        void hideLoading();

        void showEmpty();

        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadShoppCart(int uid,boolean isShowLoad);
        public abstract void deleteShoppCart(Map<String, String> map);
        public abstract void deleteOneShoppCart(Map<String, String> map,int groupPosition, int childPosition);

    }
}