package com.yizhisha.maoyi.ui.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.CouponsAdapter;
import com.yizhisha.maoyi.adapter.MyRatingAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/26.
 */

public class MyRatingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mType = 0;
    private MyRatingAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();

    public static MyRatingFragment getInstance(int type) {
        MyRatingFragment sf = new MyRatingFragment();
        sf.mType = type;
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myrating;
    }

    @Override
    protected void initView() {
        if(mType==1){
            dataLists.add("评价");
            dataLists.add("评价");
        }else if(mType==2){

            dataLists.add("已评价");
            dataLists.add("已评价");
        }
        mLoadingView.loadSuccess();
        initAdapter();
    }
    private void initAdapter() {
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MyRatingAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {

    }
}
