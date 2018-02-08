package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;

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
    @Bind(R.id.dialhotline_tv)
    TextView dialhotlineTv;
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
        dialhotlineTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NormalAlertDialog.Builder(AnswerActivity.this)
                        .setBoolTitle(false)
                        .setContentText("0769-83115811")
                        .setContentTextSize(18)
                        .setLeftText("取消")
                        .setRightText("呼叫")
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0769-81064588"));
                                startActivity(phoneIneten);
                                dialog.dismiss();

                            }
                        }).build().show();
            }
        });
    }
}
