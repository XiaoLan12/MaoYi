package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/22.
 */

public class HomeFragment extends BaseFragment {
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)

    ViewPager viewPager;
    private String[] mTitles = {"今日专场", "七日爆款","往期专场","工作室"};
    private int[] mType = {1, 2,3};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initView() {
        mFragments.add(new TodaySpecialFragment());
        mFragments.add(new SDayExplosionFragment());
        mFragments.add(new PastSpecialFragment());
        mFragments.add(new StudioFragment());
        slidingTabLayout.setViewPager(viewPager, mTitles, getActivity(), mFragments);
    }
}
