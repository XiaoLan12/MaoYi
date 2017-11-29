package com.yizhisha.maoyi.ui.me.contract;

import com.yizhisha.maoyi.base.BasePresenter;
import com.yizhisha.maoyi.base.BaseView;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public interface HaveEvaluationContract {
    interface View extends BaseView {
        void loadCommentSuccess(List<MyCommentListBean> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadComment(int uid, boolean isShowLoad);


    }
}
