package com.yizhisha.maoyi.ui.home.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public interface CommentYarnContract {
    interface View extends BaseView {
        void loadCommentListSuccess(List<MyCommentListBean> model);
        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadCommentList(int uid,boolean isShowLoad);
    }
}
