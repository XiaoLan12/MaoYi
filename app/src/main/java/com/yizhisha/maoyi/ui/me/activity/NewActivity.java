package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class NewActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(NewActivity.this);
            }
        });
    }
    @Override
    protected void initView() {

    }
    @OnClick({R.id.oline_new_rl,R.id.new_center_rl,R.id.problem1_rl,R.id.problem2_rl,R.id.problem3_rl,R.id.problem4_rl
            ,R.id.problem5_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oline_new_rl:
                new NormalAlertDialog.Builder(this)
                        .setBoolTitle(true)
                        .setTitleText("温馨提示")
                        .setContentText("功能正在开发中")
                        .setSingleModel(true)
                        .setSingleText("确认")
                        .setSingleTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                        }).build().show();
                break;
            case R.id.new_center_rl:
                new NormalAlertDialog.Builder(this)
                        .setBoolTitle(true)
                        .setTitleText("温馨提示")
                        .setContentText("功能正在开发中")
                        .setSingleModel(true)
                        .setSingleText("确认")
                        .setSingleTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }
                        }).build().show();
                break;
            case R.id.problem1_rl:
                Bundle bundle1=new Bundle();
                bundle1.putInt(AnswerActivity.PROBLEMTYPE,1);
                startActivity(AnswerActivity.class,bundle1);
                break;
            case R.id.problem2_rl:
                Bundle bundle2=new Bundle();
                bundle2.putInt(AnswerActivity.PROBLEMTYPE,2);
                startActivity(AnswerActivity.class,bundle2);
                break;
            case R.id.problem3_rl:
                Bundle bundle3=new Bundle();
                bundle3.putInt(AnswerActivity.PROBLEMTYPE,3);
                startActivity(AnswerActivity.class,bundle3);
                break;
            case R.id.problem4_rl:
                Bundle bundle4=new Bundle();
                bundle4.putInt(AnswerActivity.PROBLEMTYPE,4);
                startActivity(AnswerActivity.class,bundle4);
                break;
            case R.id.problem5_rl:
                Bundle bundle5=new Bundle();
                bundle5.putInt(AnswerActivity.PROBLEMTYPE,5);
                startActivity(AnswerActivity.class,bundle5);
                break;
        }
    }
}
