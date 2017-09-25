package com.yizhisha.maoyi.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.me.fragment.MyOrderFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MyOrderActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;

    private String[] mTitles = {"全部", "待付款", "待发货", "待收货","已完成"};
    private int[] mType = {0, 1, 2, 3, 4};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ActivityManager.getActivityMar().finishActivity(MyOrderActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        int currentIndex=bundle.getInt("INDEX");
        for (int type : mType) {
            mFragments.add(MyOrderFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager, mTitles, this, mFragments);
        slidingTabLayout.setCurrentTab(currentIndex);
    }
}
