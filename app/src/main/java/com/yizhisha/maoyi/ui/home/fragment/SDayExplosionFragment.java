package com.yizhisha.maoyi.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.bean.json.WeekTopBean;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.ui.home.contract.SDayExplosionContract;
import com.yizhisha.maoyi.ui.home.presenter.SDayExplosionPresenter;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class SDayExplosionFragment extends BaseFragment<SDayExplosionPresenter> implements SDayExplosionContract.View {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private ImageView img_banner;

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
                Bundle bundle = new Bundle();
                bundle.putInt("gid", dataLists.get(position).getId());
                startActivity(ProductDetailActivity.class,bundle);
            }
        });
//        mAdapter.setNewData(dataLists);
        addHeadView();
        mPresenter.getWeekList();
        mPresenter.getWeekTop();
    }
    private void addHeadView() {
        View view=getActivity().getLayoutInflater().inflate(R.layout.headview_sday_explosion, (ViewGroup) mRecyclerView.getParent(), false);
        img_banner=view.findViewById(R.id.img_banner);

        mAdapter.addHeaderView(view);
    }

    @Override
    public void getWeekToprSuccess(List<WeekTopBean> model) {
        Log.e("TTT",model.get(0).getSpc_litpic()+"-----");
        if(model!=null)
        GlideUtil.getInstance().LoadSupportv4FragmentBitmap(SDayExplosionFragment.this,AppConstant.BANNER_IMG_URL+model.get(0).getSpc_litpic(),img_banner,0,0,null);

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
