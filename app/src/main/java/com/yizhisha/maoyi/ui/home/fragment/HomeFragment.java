package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.ui.me.fragment.MyRatingFragment;

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
    private String[] mTitles = {"今日专场", "七日爆款","往期转场"};
    private int[] mType = {1, 2,3};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initView() {
        mFragments.add(new TodaySpecialFragment());
            mFragments.add(MyRatingFragment.getInstance(1));
        mFragments.add(new TodaySpecialFragment());
        slidingTabLayout.setViewPager(viewPager, mTitles, getActivity(), mFragments);
    }
}
