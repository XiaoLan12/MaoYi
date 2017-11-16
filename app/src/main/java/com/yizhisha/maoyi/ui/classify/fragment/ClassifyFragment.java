package com.yizhisha.maoyi.ui.classify.fragment;

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

public class ClassifyFragment extends BaseFragment {
    @Bind(R.id.slidingtablayout1)
    SlidingTabLayout slidingTabLayout1;
    @Bind(R.id.viewpager1)
    ViewPager viewPager1;

    private String[] mTitles = {"类别", "色系"};
    private int[] mType = {1, 2};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }
    @Override
    protected void initView() {
        for (int type : mType) {
            mFragments.add(new ClassificationColourFragment());
        }
        slidingTabLayout1.setViewPager(viewPager1, mTitles, getActivity(), mFragments);
    }
}
