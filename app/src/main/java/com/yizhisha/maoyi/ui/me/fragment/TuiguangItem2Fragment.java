package com.yizhisha.maoyi.ui.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.TuiguangItem2Adapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.TuiguangItem1Bean;
import com.yizhisha.maoyi.bean.json.TuiguangItem2Bean;
import com.yizhisha.maoyi.ui.me.contract.TuiguangItem1Contract;
import com.yizhisha.maoyi.ui.me.presenter.TuiguangItem1Presenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem2Fragment extends BaseFragment<TuiguangItem1Presenter> implements SwipeRefreshLayout.OnRefreshListener,
        TuiguangItem1Contract.View ,BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TuiguangItem2Adapter mAdapter;
    private List<TuiguangItem2Bean.Info> dataLists = new ArrayList<>();
    private int page=0;
    private int currentPage=0;
    @Override
    public void onRefresh() {
        page=0;
        if(dataLists.size()>0){
            load(false);
        }else{
            load(true);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupons;
    }

    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("page",String.valueOf(page));
        mPresenter.loadTuiGuangItem2(map,isShowLoad);
    }
    private void loadMore(){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("page",String.valueOf(currentPage));
        mPresenter.loadTuiGuangItem2LoreMore(map);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(activity, LinearLayoutManager.VERTICAL));
        mAdapter=new TuiguangItem2Adapter(dataLists);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setEnableLoadMore(false);

        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public void loadTuiGuangItem1Success(List<TuiguangItem1Bean.Info> data) {

    }
    @Override
    public void loadTuiGuangItem1LoreMore(List<TuiguangItem1Bean.Info> data) {

    }
    @Override
    public void loadTuiGuangItem2Success(List<TuiguangItem2Bean.Info> data) {
        dataLists.clear();
        dataLists.addAll(data);
        mAdapter.setNewData(dataLists);
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);
    }

    @Override
    public void loadTuiGuangItem2LoreMore(List<TuiguangItem2Bean.Info> data) {
        page=currentPage;
        if (data.size() == 0) {
            mAdapter.loadMoreEnd(false);
        } else {
            mAdapter.addData(data);
            dataLists = mAdapter.getData();
            mAdapter.loadMoreComplete();
        }
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
    public void loadMoreFail() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showEmpty() {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(int code, String msg) {
        mSwipeRefreshLayout.setRefreshing(false);

        if(code==0){
            ToastUtil.showShortToast(msg);
        }else{
            dataLists.clear();
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(dataLists);
            mLoadingView.loadError();
            mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
                @Override
                public void doRequestData() {
                    page=0;
                    load(true);
                }
            });
        }
    }

    @Override
    public void onLoadMoreRequested() {
        currentPage=page+1;
        loadMore();
    }
}
