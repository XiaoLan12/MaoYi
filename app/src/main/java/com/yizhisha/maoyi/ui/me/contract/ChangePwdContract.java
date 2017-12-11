package com.yizhisha.maoyi.ui.me.contract;
import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;

import java.util.Map;

/**
 * Created by lan on 2017/7/18.
 */

public interface ChangePwdContract {
    interface View extends BaseView {
        void changePwdSuccess(String msg);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void changePwd(Map<String, String> param);
    }
}
