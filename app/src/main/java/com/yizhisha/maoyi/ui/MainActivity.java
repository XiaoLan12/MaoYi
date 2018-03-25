package com.yizhisha.maoyi.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.MainTabEntity;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.ui.home.fragment.HomeFragment;
import com.yizhisha.maoyi.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.me.fragment.MeFragment;
import com.yizhisha.maoyi.ui.shoppcart.fragment.ShoppCartFragment;
import com.yizhisha.maoyi.ui.type.TypeFragment;
import com.yizhisha.maoyi.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

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
    private TypeFragment typeFragment;
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
        String welcome= (String) SharedPreferencesUtil.getValue(MainActivity.this,"WELCOME","");
        if(welcome.equals("")){
            startActivity(WelcomeActivity.class);
            finish();
        }
        //初始化选项卡
        /*AppConstant.isLogin = (boolean) SharedPreferencesUtil.getValue(this, "ISLOGIN",new Boolean(false));
        AppConstant.UID= (int) SharedPreferencesUtil.getValue(this,"UID",new Integer(0));*/
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
                if(position==2){
                    if(AppConstant.isLogin==false){
                        final List<String> mDatas1=new ArrayList<>();
                        mDatas1.add("登录");
                        mDatas1.add("注册");
                        NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(MainActivity.this)
                                .setBoolTitle(true)
                                .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                                .setTitleHeight(55)
                                .setItemHeight(45)
                                .setItemTextColor(R.color.blue)
                                .setItemTextSize(14)
                                .setItemWidth(0.95f)
                                .setCancleButtonText("取消")
                                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                                    @Override
                                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                        switch (position){
                                            case 0:
                                                startActivity(LoginFragmentActivity.class);
                                                break;
                                            case 1:
                                                startActivity(RegisterActivity.class);
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                }).setTouchOutside(true)
                                .build();
                        dialog.setData(mDatas1);
                        dialog.show();
                        tabLayout.setCurrentTab(currentPosition);
                        return;
                    }
                }
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
            typeFragment= (TypeFragment) getSupportFragmentManager().findFragmentByTag("typeFragment");
            shoppCartFragment = (ShoppCartFragment) getSupportFragmentManager().findFragmentByTag("shoppCartFragment");
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("meFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            typeFragment=new TypeFragment();
            shoppCartFragment = new ShoppCartFragment();
            meFragment = new MeFragment();
            transaction.add(R.id.fl_body, homeFragment, "homeFragment");
            transaction.add(R.id.fl_body, typeFragment, "typeFragment");
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
                transaction.hide(typeFragment);
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
                transaction.show(typeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //购物车
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(meFragment);
                transaction.hide(typeFragment);
                transaction.show(shoppCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            //个人中心
            case 3:
                transaction.hide(homeFragment);
                transaction.hide(typeFragment);
                transaction.hide(shoppCartFragment);
                transaction.show(meFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
