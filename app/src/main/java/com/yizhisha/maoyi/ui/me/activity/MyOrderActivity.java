package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.me.fragment.MyOrderFragment;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {

    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.order_ll)
    //搜索状态
    LinearLayout toobarLl;
    @Bind(R.id.search_et)
    ClearEditText searchEt;
    @Bind(R.id.back_iv)
    ImageView backIv;
    @Bind(R.id.query_tv)
    TextView queryTv;
    //默认状态
    @Bind(R.id.order_normal__rl)
    RelativeLayout toobarNormalRl;
    @Bind(R.id.back_normal_iv)
    ImageView backNormalIv;
    @Bind(R.id.query_normal_iv)
    ImageView queryNormalIv;

    private String[] mTitles = {"全部", "待付款", "待发货", "待收货","已完成"};
    private int[] mType = {-1,0, 1, 2, 3};
    public String key;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyOrderFragment currfragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }
    @Override
    protected void initToolBar() {

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

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                key=s.toString();
                if(s.length()==0){
                    queryTv.setText("取消");
                }else{
                    queryTv.setText("搜索");
                }
            }
        });
    }
    @OnClick({R.id.query_normal_iv,R.id.query_tv,R.id.back_iv,R.id.back_normal_iv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_normal_iv:
                toobarNormalRl.setVisibility(View.GONE);
              /*  MyOrderFragment fragment= (MyOrderFragment) mFragments.get(slidingTabLayout.getCurrentTab());
                fragment.search(searchEt.getText().toString().trim());*/
                break;
            case R.id.query_tv:
                String text=queryTv.getText().toString().trim();
                if(text.equals("搜索")){
                    currfragment= (MyOrderFragment) mFragments.get(slidingTabLayout.getCurrentTab());
                    Bundle bundle=new Bundle();
                    bundle.putInt("STATUS",currfragment.getStatus());
                    bundle.putString("KEY",key);
                    startActivityForResult(OrderSearchActivity.class,bundle,101);
                }else{
                    toobarNormalRl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.back_iv:
                ActivityManager.getActivityMar().finishActivity(MyOrderActivity.this);
                break;
            case R.id.back_normal_iv:
                ActivityManager.getActivityMar().finishActivity(MyOrderActivity.this);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(currfragment!=null){
            currfragment.onRefresh();
        }
    }
}
