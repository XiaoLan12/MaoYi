package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public interface TodaySpecialContract {
    interface View extends BaseView {
        void getDailyTopSliderSuccess(List<WeekTopListBean.WeekTopBean> model);
        void getDailyListSuccess(ListBean<DailyBean> model);
        void loadFail(String msg);
        void showLoading();
        void hideLoading();
    }

    abstract class Presenter extends BasePresenter<TodaySpecialContract.View> {

        public abstract void getDailyTopSlider();
        public abstract void getDailyList(boolean isShowLoad);

    }
}
