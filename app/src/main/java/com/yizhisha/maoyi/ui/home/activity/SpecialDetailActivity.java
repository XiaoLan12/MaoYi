package com.yizhisha.maoyi.ui.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.SDayExplosionAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.SpecialDetailBean;
import com.yizhisha.maoyi.bean.json.WeekListBean;
import com.yizhisha.maoyi.ui.home.contract.SpecialDetailContract;
import com.yizhisha.maoyi.ui.home.presenter.SpecialDetailPresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SpecialDetailActivity extends BaseActivity<SpecialDetailPresenter> implements SpecialDetailContract.View {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private Banner banner;
    private List<String> imageUrl;
    private SDayExplosionAdapter mAdapter;
    private List<WeekListBean> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_detail;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() { mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mAdapter = new SDayExplosionAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setNewData(dataLists);
        addHeadView();
        Map<String,String> map=new HashMap<>();
        map.put("spc_id","2");
        mPresenter.getSpecialDetail(map);
    }
    private void addHeadView() {
        View view=getLayoutInflater().inflate(R.layout.headview_special_detail, (ViewGroup) mRecyclerView.getParent(), false);
        banner=view.findViewById(R.id.banner);

        mAdapter.addHeaderView(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getSpecialDetailSuccess(SpecialDetailBean model) {
        dataLists=model.getGoods();
        mAdapter.setNewData(dataLists);

        imageUrl = new ArrayList<>();
        for(int i=0;i<model.getSpecial().size();i++){
            imageUrl.add(AppConstant.BANNER_IMG_URL+model.getSpecial().get(i).getSpc_litpic());
        }
//        imageUrl.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        if(model.getSpecial().size()<2){
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        }else{
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        }

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrl);
//        banner.setBannerTitles(bannerTitle);
        banner.setDelayTime(3000);
        banner.start();

    }

    @Override
    public void loadFail(String msg) {

    }
    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
