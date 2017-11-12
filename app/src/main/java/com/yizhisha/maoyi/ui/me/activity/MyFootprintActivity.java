package com.yizhisha.maoyi.ui.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyCollectAdapter;
import com.yizhisha.maoyi.adapter.MyFootprintAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.FootpringBean;
import com.yizhisha.maoyi.ui.me.contract.MyFootprintContract;
import com.yizhisha.maoyi.ui.me.presenter.MyFootprintPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class MyFootprintActivity extends BaseActivity<MyFootprintPresenter> implements MyFootprintContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyFootprintAdapter mAdapter;
    private List<FootpringBean.History> dataLists=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_footprint;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(MyFootprintActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        mPresenter.loadFootPrintr(map,isShowLoad);

    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new MyFootprintAdapter(dataLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(this,RecyclerViewDriverLine.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        /*mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.delete_tv:
                        new NormalAlertDialog.Builder(MyFootprintActivity.this)
                                .setBoolTitle(false)
                                .setContentText("确定删除该条数据吗?")
                                .setLeftText("取消")
                                .setRightText("确认")
                                .setWidth(0.75f)
                                .setHeight(0.33f)
                                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                    @Override
                                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                                        Map<String,String> map=new HashMap<String, String>();
                                        map.put("id",dataLists.get(position).getId());
                                        mPresenter.clearFootPrint(map);
                                        dialog.dismiss();

                                    }
                                }).build().show();

                        break;
                }
            }
        });*/
       /* mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", dataLists.get(position).getTypeid());
                startActivity(YarnActivity.class, bundle);
            }
        });
*/
    }
    @Override
    public void onRefresh() {
        load(false);
    }
    @Override
    public void loadSuccess(List<FootpringBean.History> data) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataLists.addAll(data);
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void clearFootPrint(String msg) {
        load(false);
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
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
        }
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                 load(true);
            }
        });
    }
}
