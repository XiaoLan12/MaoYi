package com.yizhisha.maoyi.ui.type.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.bean.json.StudioShopBean;

import java.util.List;
import java.util.Map;

/**
 * Created by 小蓝 on 2018/3/21.
 */

public interface TypeContract {
    interface View extends BaseView {

        void getStudioSuccess(List<StudioBean.StudioListBean> model);
        void getSortedListSuccess(List<SortedListBean.SortedsBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSortedList();
        public abstract void getStudio();

    }
}
