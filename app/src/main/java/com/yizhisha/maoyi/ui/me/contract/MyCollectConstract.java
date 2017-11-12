package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.CollectListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/3.
 */

public interface MyCollectConstract {
    interface View extends BaseView {
        void loadCollectSuccess(List<CollectListBean.Favorite> data);

        void cacheSuccess(String str);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadCollect(Map<String, String> param,boolean isShowLoad);
        public abstract void cacheCollect(Map<String, String> param);
    }
}
