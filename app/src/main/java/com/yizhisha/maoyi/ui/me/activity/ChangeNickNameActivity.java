package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

public class ChangeNickNameActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.nickname_et)
    ClearEditText nicknameEt;
    @Bind(R.id.sure_btn)
    Button sureBtn;

    @Override
    protected int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, RescourseUtil.getColor(R.color.common_divider_narrow));
        return R.layout.activity_change_nick_name;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(ChangeNickNameActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        final String nickName=nicknameEt.getText().toString().trim();
        if(nickName.equals("")){
            ToastUtil.showShortToast("请输入新的昵称");
            return;
        }
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();

                Bundle bundle=new Bundle();
                bundle.putString("NICKNAME",nickName);
                intent.putExtras(bundle);
                setResult(PersonalInfoActivity.NICKNAME_RESULT,intent);
                finish_Activity(ChangeNickNameActivity.this);
            }
        });
    }

}
