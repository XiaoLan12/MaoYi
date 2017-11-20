package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.WeekTopBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public interface TodaySpecialContract {
    interface View extends BaseView {
        void getDailyTopSliderSuccess(List<WeekTopBean> model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<TodaySpecialContract.View> {

        public abstract void getDailyTopSlider();

    }
}
