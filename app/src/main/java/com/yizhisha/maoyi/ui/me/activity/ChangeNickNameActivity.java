package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;

import qiu.niorgai.StatusBarCompat;

public class ChangeNickNameActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, RescourseUtil.getColor(R.color.common_divider_narrow));
        return R.layout.activity_change_nick_name;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {

    }
}
