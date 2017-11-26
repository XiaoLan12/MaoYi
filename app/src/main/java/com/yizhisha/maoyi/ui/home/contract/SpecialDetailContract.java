package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */

public interface SpecialDetailContract {
    interface View extends BaseView {

        void getSpecialDetailSuccess(SpecialDetailBean model);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<SpecialDetailContract.View> {

        public abstract void getSpecialDetail(Map<String,String> map);

    }
}
