package com.yizhisha.maoyi.ui.classify.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.ClassificationColourAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/12 0012.
 */

public class ClassificationColourFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private ClassificationColourAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classfition_colour;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        for(int i=0;i<10;i++){
            dataLists.add("ll");
        }
        mAdapter = new ClassificationColourAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);


    }
}
