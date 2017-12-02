package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public interface ApplyRefundContract {
    interface View extends BaseView {
        void addRefundSuccess(String data);
        void addPicSuccess(String img);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void addRefund(Map<String,String> map);
        public abstract void addPic(MultipartBody.Part body);
    }
}
