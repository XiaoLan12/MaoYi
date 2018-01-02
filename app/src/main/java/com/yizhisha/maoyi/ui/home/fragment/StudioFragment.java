package com.yizhisha.maoyi.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.HaveEvaluationAdapter;
import com.yizhisha.maoyi.adapter.StudioAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.ui.home.activity.StudioShopActivity;
import com.yizhisha.maoyi.ui.home.contract.StudioContract;
import com.yizhisha.maoyi.ui.home.presenter.StudioPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioFragment extends BaseFragment<StudioPresenter> implements StudioContract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private StudioAdapter mAdapter;
    private ArrayList<StudioBean.StudioListBean> dataList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_studio;
    }
    @Override
    protected void initView() {
        initAdapter();
        mPresenter.getStudio(true);
    }
    private void initAdapter(){

        mAdapter=new StudioAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("wid",dataList.get(position).getId());
                startActivity(StudioShopActivity.class,bundle);
            }
        });
    }
    @Override
    public void getStudioSuccess(List<StudioBean.StudioListBean> model) {
        dataList.clear();
        dataList.addAll(model);
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
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.getStudio(true);
            }
        });
    }
}
