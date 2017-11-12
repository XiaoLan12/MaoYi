package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.adapter.TodaySpecilAdapter;
import com.yizhisha.maoyi.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class SDayExplosionFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private SDayExplosionAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sday_explosion;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        for(int i=0;i<10;i++){
            dataLists.add("ll");
        }
        mAdapter = new SDayExplosionAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setNewData(dataLists);
        addHeadView();
    }
    private void addHeadView() {
        View view=getActivity().getLayoutInflater().inflate(R.layout.headview_sday_explosion, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.addHeaderView(view);
    }
}
