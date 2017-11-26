package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopBean;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.ui.home.contract.SDayExplosionContract;
import com.yizhisha.maoyi.ui.home.presenter.SDayExplosionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class SDayExplosionFragment extends BaseFragment<SDayExplosionPresenter> implements SDayExplosionContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sday_explosion;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new SDayExplosionAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ProductDetailActivity.class);
            }
        });
//        mAdapter.setNewData(dataLists);
        addHeadView();
        mPresenter.getWeekList();
    }
    private void addHeadView() {
        View view=getActivity().getLayoutInflater().inflate(R.layout.headview_sday_explosion, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.addHeaderView(view);
    }

    @Override
    public void getWeekToprSuccess(List<WeekTopBean> model) {

    }

    @Override
    public void getWeekListtSuccess(List<WeekListBean> model) {
        dataLists=model;
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {

    }
}
