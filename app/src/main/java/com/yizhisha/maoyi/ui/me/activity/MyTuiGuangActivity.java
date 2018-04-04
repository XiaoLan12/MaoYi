package com.yizhisha.maoyi.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.me.fragment.HaveEvaluationFragment;
import com.yizhisha.maoyi.ui.me.fragment.ToEvaluationFragment;
import com.yizhisha.maoyi.ui.me.fragment.TuiguangItem1Fragment;
import com.yizhisha.maoyi.ui.me.fragment.TuiguangItem2Fragment;
import com.yizhisha.maoyi.utils.DateUtil;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.Bind;

public class MyTuiGuangActivity extends BaseActivity implements TuiguangItem1Fragment.FragmentInteraction {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.user_photo_iv)
    ImageView userPhotoIv;
    @Bind(R.id.amount_tv)
    TextView amountTv;
    @Bind(R.id.user_name_tv)
    TextView userNameTv;
    @Bind(R.id.member_level_tv)
    TextView memberLevelTv;
    @Bind(R.id.member_num_tv)
    TextView memberNumTv;
    @Bind(R.id.join_time_tv)
    TextView joiinTimeTv;

    private String[] mTitles = {"收入明细", "人脉资源"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tui_guang;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyTuiGuangActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        if(AppConstant.meInfoBean!=null) {
            memberNumTv.setText(AppConstant.meInfoBean.getInvitecode());
            joiinTimeTv.setText(DateUtil.getDateToString1(AppConstant.meInfoBean.getRegtime() * 1000));
            userNameTv.setText(AppConstant.meInfoBean.getNickname());
            memberLevelTv.setText("普通会员");
            GlideUtil.getInstance().LoadContextCircleBitmap(this, AppConstant.HEAD_IMG_URL + AppConstant.meInfoBean.getAvatar(), userPhotoIv,
                    R.drawable.icon_head_normal, R.drawable.icon_head_normal);
        }
        mFragments.add(new TuiguangItem1Fragment());
        mFragments.add(new TuiguangItem2Fragment());
        slidingTabLayout.setViewPager(viewPager, mTitles, this, mFragments);
    }

    @Override
    public void process(String str) {
        amountTv.setText(str);
    }
}
