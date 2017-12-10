package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.utils.RescourseUtil;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class SettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    protected void initToolBar(){
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(SettingActivity.this);
            }
        });
    }
    @Override
    protected void initView(){

    }
    @OnClick({R.id.personalInfo_rl,R.id.changePwd_rl,R.id.goodaddress_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personalInfo_rl:
                startActivity(PersonalInfoActivity.class);
                break;
            case R.id.changePwd_rl:
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.goodaddress_rl:
                startActivity(MyAddressActivity.class);
                break;
        }
    }
}
