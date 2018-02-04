package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;

import butterknife.Bind;

public class AnswerActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.problem_answer1_ll)
    LinearLayout problemAnswer1Ll;
    @Bind(R.id.problem_answer2_ll)
    LinearLayout problemAnswer2Ll;
    @Bind(R.id.problem_answer3_ll)
    LinearLayout problemAnswer3Ll;
    @Bind(R.id.problem_answer4_ll)
    LinearLayout problemAnswer4Ll;
    @Bind(R.id.problem_answer5_ll)
    LinearLayout problemAnswer5Ll;
    public static final String PROBLEMTYPE="TYPE";
    private int type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(AnswerActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt(PROBLEMTYPE,1);
        switch (type){
            case 1:
                problemAnswer1Ll.setVisibility(View.VISIBLE);
                problemAnswer2Ll.setVisibility(View.GONE);
                problemAnswer3Ll.setVisibility(View.GONE);
                problemAnswer4Ll.setVisibility(View.GONE);
                problemAnswer5Ll.setVisibility(View.GONE);

                break;
            case 2:
                problemAnswer1Ll.setVisibility(View.GONE);
                problemAnswer2Ll.setVisibility(View.VISIBLE);
                problemAnswer3Ll.setVisibility(View.GONE);
                problemAnswer4Ll.setVisibility(View.GONE);
                problemAnswer5Ll.setVisibility(View.GONE);
                break;
            case 3:
                problemAnswer1Ll.setVisibility(View.GONE);
                problemAnswer2Ll.setVisibility(View.GONE);
                problemAnswer3Ll.setVisibility(View.VISIBLE);
                problemAnswer4Ll.setVisibility(View.GONE);
                problemAnswer5Ll.setVisibility(View.GONE);
                break;
            case 4:
                problemAnswer1Ll.setVisibility(View.GONE);
                problemAnswer2Ll.setVisibility(View.GONE);
                problemAnswer3Ll.setVisibility(View.GONE);
                problemAnswer4Ll.setVisibility(View.VISIBLE);
                problemAnswer5Ll.setVisibility(View.GONE);
                break;
            case 5:
                problemAnswer1Ll.setVisibility(View.GONE);
                problemAnswer2Ll.setVisibility(View.GONE);
                problemAnswer3Ll.setVisibility(View.GONE);
                problemAnswer4Ll.setVisibility(View.GONE);
                problemAnswer5Ll.setVisibility(View.VISIBLE);
                break;

        }
    }
}
