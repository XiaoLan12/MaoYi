package com.yizhisha.maoyi.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.me.fragment.CouponsFragment;
import com.yizhisha.maoyi.ui.me.fragment.MyOrderFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class CouponsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private String[] mTitles = {"未使用", "已使用", "已过期"};
    private int[] mType = {1, 2, 3};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupons;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(CouponsActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        for (int type : mType) {
            mFragments.add(CouponsFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager, mTitles, this, mFragments);
    }
}
