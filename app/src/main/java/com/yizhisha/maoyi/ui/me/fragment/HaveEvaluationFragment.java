package com.yizhisha.maoyi.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.HaveEvaluationAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.DataHelper;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.ui.me.contract.HaveEvaluationContract;
import com.yizhisha.maoyi.ui.me.presenter.HaveEvaluatioPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class HaveEvaluationFragment extends BaseFragment<HaveEvaluatioPresenter> implements HaveEvaluationContract.View
,SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HaveEvaluationAdapter mAdapter;
    private ArrayList<Object> dataList=new ArrayList<>();
    public static HaveEvaluationFragment getInstance() {
        HaveEvaluationFragment sf = new HaveEvaluationFragment();
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_haveevaluation;
    }
    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter=new HaveEvaluationAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(dataList.get(position) instanceof MyCommentListBean.Goods) {
                    MyCommentListBean.Goods goods= (MyCommentListBean.Goods) dataList.get(position);
                    if(adapter.getItemViewType(position)==2){
                        Bundle bundle = new Bundle();
                        bundle.putInt("TYPE",1);
                        bundle.putInt("id", goods.getGid());
                        //startActivity(YarnActivity.class, bundle);
                    }
                }
            }
        });

    }
    private void load(boolean isShowLoad){
        mPresenter.loadComment(AppConstant.UID,isShowLoad);
    }
    @Override
    public void loadCommentSuccess(List<MyCommentListBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getCommentDataAfterHandle(data);
        mAdapter.setNewData(dataList);
    }
    @Override
    public void showLoading() {
        mLoadingView.load();
    }
    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }

    @Override
    public void showEmpty() {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                 load(true);
            }
        });
    }


    @Override
    public void onRefresh() {
        load(false);
    }
}
