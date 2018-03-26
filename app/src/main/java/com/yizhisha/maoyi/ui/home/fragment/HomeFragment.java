package com.yizhisha.maoyi.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toolbar;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.home.activity.SearchActivity;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by lan on 2017/9/22.
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
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
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.class);
            }
        });
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
        mFragments.add(new TodaySpecialFragment());
        mFragments.add(new SDayExplosionFragment());
        mFragments.add(new PastSpecialFragment());
        mFragments.add(new StudioFragment());
        slidingTabLayout.setViewPager(viewPager, mTitles, getActivity(), mFragments);
    }
}
