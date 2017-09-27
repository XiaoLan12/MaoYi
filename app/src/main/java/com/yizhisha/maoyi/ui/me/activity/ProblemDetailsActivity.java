package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.utils.RescourseUtil;

import org.w3c.dom.Text;

import butterknife.Bind;
import qiu.niorgai.StatusBarCompat;

public class ProblemDetailsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.problem_type_tv)
    TextView problemTypeTv;
    @Override
    protected int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, RescourseUtil.getColor(R.color.common_divider_narrow));
        return R.layout.activity_problem_details;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(ProblemDetailsActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        String type=bundle.getString("TYPE");
        problemTypeTv.setText(type);

    }
}
