package com.yizhisha.maoyi.ui.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.CouponsAdapter;
import com.yizhisha.maoyi.adapter.MyOrderAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;
import com.yizhisha.maoyi.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/26.
 */

public class CouponsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mType = 0;
    private CouponsAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();

    public static CouponsFragment getInstance(int type) {
        CouponsFragment sf = new CouponsFragment();
        sf.mType = type;
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupons;
    }

    @Override
    protected void initView() {
        if(mType==1){
            dataLists.add("未使用");
            dataLists.add("未使用");
        }else if(mType==2){

            dataLists.add("已使用");
            dataLists.add("已使用");
        }else{
            dataLists.add("已过期");
            dataLists.add("已过期");
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
        mAdapter = new CouponsAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {

    }
}
