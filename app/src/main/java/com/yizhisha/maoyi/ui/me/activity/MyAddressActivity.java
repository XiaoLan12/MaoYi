package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyAddressAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.ui.me.contract.MyAddressContract;
import com.yizhisha.maoyi.ui.me.presenter.MyAddressPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAddressActivity extends BaseActivity<MyAddressPresenter> implements MyAddressContract.View,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @Bind(R.id.loadingView)
    CommonLoadingView loadingView;

    private MyAddressAdapter mAdapter;

    private List<GoodsListBean.Address> dataList=new ArrayList<>();

    private int index;

    private int mType;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(MyAddressActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mType=bundle.getInt("TYPE");
        }
        initAdapter();
        mPresenter.loadAddress(AppConstant.UID,true);
    }
    private void initAdapter(){
        swiperefreshlayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        swiperefreshlayout.setProgressViewEndTarget(false, 100);
        swiperefreshlayout.setOnRefreshListener(this);

        mAdapter=new MyAddressAdapter(dataList);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(mAdapter);
        recyclerview.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mType==1) {
                  /*  Intent intent = new Intent();
                    intent.putExtra("ADDRESS", dataList.get(position));
                    setResult(2, intent);
                    finish_Activity(MyAddressActivity.this);*/
                }
            }
        });
        recyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.edit_myaddress_tv:
                        Bundle bundle=new Bundle();
                        bundle.putInt("TYPE",1);
                        bundle.putSerializable("ADDRESS", (Serializable) dataList.get(position));
                        startActivityForResult(AddAddressActivity.class,bundle,2);
                        break;
                    case R.id.delete_myaddress_tv:
                        new NormalAlertDialog.Builder(MyAddressActivity.this)
                                .setBoolTitle(false)
                                .setContentText("确定要删除该地址吗?")
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
                                      /*  mPresenter.deleteAddress(dataList.get(position).getId());
                                        dataList.remove(position);*/
                                        dialog.dismiss();

                                    }
                                }).build().show();

                        break;
                    case R.id.seletaddress_myaddress_cb:
                        Map<String,String> map=new HashMap<String, String>();
                        map.put("uid",String.valueOf(AppConstant.UID));
                        map.put("id",String.valueOf(dataList.get(position).getId()));
                        map.put("index",String.valueOf(1));
                        mPresenter.setDefaulsAddress(map);
                        index=position;

                        break;
                }
            }
        });
    }
    @Override
    public void loadAddressSuccess(List<GoodsListBean.Address> data) {
        swiperefreshlayout.setRefreshing(false);
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void setDefaulsAddressSuccess(String msg) {
        for(int i=0;i<dataList.size();i++){
            dataList.get(i).setIndex("0");
        }
        dataList.get(index).setIndex("1");
        mAdapter.setNewData(dataList);
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void showLoading() {
        loadingView.load();
    }

    @Override
    public void hideLoading() {
        loadingView.loadSuccess();
    }

    @Override
    public void showEmpty() {
        dataList.clear();
        swiperefreshlayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        loadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(int code, String msg) {
        if(code==0){
            ToastUtil.showShortToast(msg);
        }else{
            loadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
                @Override
                public void doRequestData() {
                    mPresenter.loadAddress(AppConstant.UID,true);
                }
            });
            dataList.clear();
            swiperefreshlayout.setRefreshing(false);
            mAdapter.setNewData(dataList);
            loadingView.loadError();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.loadAddress(AppConstant.UID,false);
    }
}
