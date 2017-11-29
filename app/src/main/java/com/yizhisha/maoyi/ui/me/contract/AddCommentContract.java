package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;

import java.util.Map;
import okhttp3.MultipartBody;

/**
 * Created by lan on 2017/8/8.
 */

public interface AddCommentContract {
    interface View extends BaseView {
        void addCommentSuccess(String data);
        void addAddCommentSuccess(String data);
        void addCommentPicSuccess(String img);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void addComment(Map<String,String> map);
        public abstract void addAddComment(Map<String,String> map);
        public abstract void addCommentPic(MultipartBody.Part body);
    }
}