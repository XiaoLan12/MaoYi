package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.PastSpecilAdapter;
import com.yizhisha.maoyi.adapter.TodaySpecilAdapter;
import com.yizhisha.maoyi.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class PastSpecialFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private PastSpecilAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_past_special;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        for(int i=0;i<10;i++){
            dataLists.add("ll");
        }
        mAdapter = new PastSpecilAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);
    }
}
