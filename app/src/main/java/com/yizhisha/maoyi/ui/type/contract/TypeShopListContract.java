package com.yizhisha.maoyi.ui.type.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;

import java.util.List;
import java.util.Map;

/**
 * Created by 小蓝 on 2018/3/24.
 */

public interface TypeShopListContract {
    interface View extends BaseView {

        void getGoodsSortedSuccess(SpecialDetailBean model);
        void getSortedListSuccess(List<SortedListBean.SortedsBean> model);
        void getStudioShopSuccess(StudioShopBean model);

        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(int code,String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getGoodsSorted(Map<String,String> map,boolean isShowLoad);
        public abstract void getStudioShop(Map<String,String> map,boolean isShowLoad);
        public abstract void getSortedList();

    }
}
