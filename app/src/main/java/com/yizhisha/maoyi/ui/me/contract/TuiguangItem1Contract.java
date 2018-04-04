package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.TuiguangItem1Bean;
import com.yizhisha.maoyi.bean.json.TuiguangItem2Bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public interface TuiguangItem1Contract {
    interface View extends BaseView {
        void loadTuiGuangItem1Success(List<TuiguangItem1Bean.Info> data);
        void loadTuiGuangItem1LoreMore(List<TuiguangItem1Bean.Info> data);
        void loadTuiGuangItem2Success(List<TuiguangItem2Bean.Info> data);
        void loadTuiGuangItem2LoreMore(List<TuiguangItem2Bean.Info> data);
        void showLoading();
        void hideLoading();
        void loadMoreFail();
        void showEmpty();
        void loadFail(int code,String msg);

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadTuiGuangItem1(Map<String, String> param, boolean isShowLoad);
        public abstract void loadTuiGuangItem1LoreMore(Map<String, String> param);
        public abstract void loadTuiGuangItem2(Map<String, String> param, boolean isShowLoad);
        public abstract void loadTuiGuangItem2LoreMore(Map<String, String> param);

    }
}
