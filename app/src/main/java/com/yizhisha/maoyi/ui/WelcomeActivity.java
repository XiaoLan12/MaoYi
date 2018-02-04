package com.yizhisha.maoyi.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.ui.home.fragment.TodaySpecialFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/2/4 0004.
 */

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_comming)
    TextView tv_comming;
    private List<Integer> list=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_welcome;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
      list.add(R.drawable.welcome1);
        list.add(R.drawable.welcome2);
        list.add(R.drawable.welcome3);


//        imageUrl.add("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(list);
        tv_comming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainActivity.class);
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==3){
                    tv_comming.setVisibility(View.VISIBLE);
                }else{
                    tv_comming.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        banner.setDelayTime(3000);

        banner.isAutoPlay(false);
        banner.start();
    }
    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片用法
            Glide.with(context).load(path) .asBitmap().into(imageView);
        }
    }
}
