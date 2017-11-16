package com.yizhisha.maoyi.ui.home.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyRatingAdapter;
import com.yizhisha.maoyi.adapter.TodaySpecilAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/11/5.
 */

public class TodaySpecialFragment extends BaseFragment {

   private  Banner banner;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<String> imageUrl;

    private TodaySpecilAdapter mAdapter;
    private List<String> dataLists = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today_special;
    }

    @Override
    protected void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        for(int i=0;i<10;i++){
            dataLists.add("ll");
        }
        mAdapter = new TodaySpecilAdapter(dataLists);
        mRecyclerView.setAdapter(mAdapter);

//        mAdapter.setNewData(dataLists);
        addHeadView();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ProductDetailActivity.class);
            }
        });


    }

    private void addHeadView() {
        View view=getActivity().getLayoutInflater().inflate(R.layout.headview_today_specil, (ViewGroup) mRecyclerView.getParent(), false);
        banner=view.findViewById(R.id.banner);
        initData();
        initView1();
        mAdapter.addHeaderView(view);
    }

    private void initData() {

        //图片地址
        imageUrl = new ArrayList<>();
        imageUrl.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        imageUrl.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        imageUrl.add("http://img.taopic.com/uploads/allimg/121017/234940-12101FR22825.jpg");

    }

    private void initView1() {

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrl);
//        banner.setBannerTitles(bannerTitle);
        banner.setDelayTime(3000);
        banner.start();
    }
     class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path).into(imageView);
        }
    }
}
