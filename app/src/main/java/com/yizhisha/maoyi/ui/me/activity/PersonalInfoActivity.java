package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.utils.RescourseUtil;

import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class PersonalInfoActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, RescourseUtil.getColor(R.color.common_divider_narrow));
        return R.layout.activity_personal_info;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {

    }
    @OnClick({R.id.nickname_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nickname_rl:
                startActivity(ChangeNickNameActivity.class);
                break;
        }
    }
}
