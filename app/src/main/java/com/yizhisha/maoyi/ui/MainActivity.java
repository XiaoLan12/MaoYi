package com.yizhisha.maoyi.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.MainTabEntity;
import com.yizhisha.maoyi.ui.classify.fragment.ClassifyFragment;
import com.yizhisha.maoyi.ui.home.fragment.HomeFragment;
import com.yizhisha.maoyi.ui.me.fragment.MeFragment;
import com.yizhisha.maoyi.ui.shoppcart.fragment.ShoppCartFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页","分类","购物车","个人中心"};
    private int[]  mIconSelectIds = {
            R.drawable.index_home,R.drawable.index_catalog,R.drawable.index_trolley
            ,R.drawable.index_user};
    private int[] mIconUnselectIds = {
            R.drawable.index_home_gray,R.drawable.index_catalog_gray,R.drawable.index_trolley_gray
            ,R.drawable.index_user_gray};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private ShoppCartFragment shoppCartFragment;
    private MeFragment meFragment;
    private int currentPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0,0);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initTab();
    }
    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MainTabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            classifyFragment= (ClassifyFragment) getSupportFragmentManager().findFragmentByTag("classifyFragment");
            shoppCartFragment = (ShoppCartFragment) getSupportFragmentManager().findFragmentByTag("shoppCartFragment");
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("meFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            classifyFragment=new ClassifyFragment();
            shoppCartFragment = new ShoppCartFragment();
            meFragment = new MeFragment();
            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
            transaction.add(R.id.fl_body, classifyFragment, "classifyFragment");
            transaction.add(R.id.fl_body, shoppCartFragment, "shoppCartFragment");
            transaction.add(R.id.fl_body, meFragment, "meFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }
    /**
     * 切换
     */
    private void SwitchTo(int position) {
        currentPosition=position;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(classifyFragment);
                transaction.hide(shoppCartFragment);
                transaction.hide(meFragment);
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //分类
            case 1:
                transaction.hide(homeFragment);
                transaction.hide(shoppCartFragment);
                transaction.hide(meFragment);
                transaction.show(classifyFragment);
                transaction.commitAllowingStateLoss();
                break;
            //购物车
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(meFragment);
                transaction.hide(classifyFragment);
                transaction.show(shoppCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            //个人中心
            case 3:
                transaction.hide(homeFragment);
                transaction.hide(classifyFragment);
                transaction.hide(shoppCartFragment);
                transaction.show(meFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
