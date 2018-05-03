package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;

import butterknife.Bind;

public class AboutUsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(AboutUsActivity.this);
            }
        });
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
    }

    @Override
    protected void initView() {

    }
}
