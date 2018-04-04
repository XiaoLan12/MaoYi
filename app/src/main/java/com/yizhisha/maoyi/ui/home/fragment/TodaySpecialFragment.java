package com.yizhisha.maoyi.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.TodaySpecilAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.json.DailyBean;
import com.yizhisha.maoyi.bean.json.ListBean;
import com.yizhisha.maoyi.bean.json.WeekTopListBean;
import com.yizhisha.maoyi.ui.home.activity.SpecialDetailActivity;
import com.yizhisha.maoyi.ui.home.contract.TodaySpecialContract;
import com.yizhisha.maoyi.ui.home.presenter.TodaySpecialPresenter;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class TodaySpecialFragment extends BaseFragment<TodaySpecialPresenter>  implements TodaySpecialContract.View {

    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;


    private  Banner banner;
    private List<String> imageUrl;

    private TodaySpecilAdapter mAdapter;
    private List<DailyBean> dataLists = new ArrayList<>();
    private  View view;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today_special;
    }

    @Override
    protected void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new TodaySpecilAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setNewData(dataLists);
        addHeadView();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("spc_id",mAdapter.getData().get(position).getSpc_id());
                startActivity(SpecialDetailActivity.class,bundle);
            }
        });


    }

    private void addHeadView() {
         view=getActivity().getLayoutInflater().inflate(R.layout.headview_today_specil, (ViewGroup) mRecyclerView.getParent(), false);
        banner=(Banner)view.findViewById(R.id.banner);
        mPresenter.getDailyTopSlider();
        mPresenter.getDailyList(false);
        mAdapter.addHeaderView(view);
    }



    @Override
    public void getDailyTopSliderSuccess(List<WeekTopListBean.WeekTopBean> model) {
        imageUrl = new ArrayList<>();
        for(int i=0;i<model.size();i++){
            imageUrl.add(AppConstant.BANNER_IMG_URL+model.get(i).getSpc_litpic());
        }
//        imageUrl.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrl);
//        banner.setBannerTitles(bannerTitle);
        banner.setDelayTime(3000);
        banner.start();
    }

    @Override
    public void getDailyListSuccess(ListBean<DailyBean> model) {
        dataLists=model.getList();
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void loadFail(String msg) {
        dataLists.clear();
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.getDailyList(true);
                mPresenter.getDailyTopSlider();
            }
        });
    }

    @Override
    public void showLoading() {
        mLoadingView.load();
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
