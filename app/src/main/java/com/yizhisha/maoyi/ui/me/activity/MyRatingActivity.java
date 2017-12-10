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
import com.yizhisha.maoyi.ui.me.fragment.HaveEvaluationFragment;
import com.yizhisha.maoyi.ui.me.fragment.ToEvaluationFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MyRatingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private String[] mTitles = {"待评价","可追评","已评价"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_rating;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyRatingActivity.this);
            }
        });
    }
    @Override
    protected void initView() {

            mFragments.add(ToEvaluationFragment.getInstance(3));
        mFragments.add(ToEvaluationFragment.getInstance(4));
            mFragments.add(HaveEvaluationFragment.getInstance());
        slidingTabLayout.setViewPager(viewPager, mTitles, this, mFragments);
    }
}
