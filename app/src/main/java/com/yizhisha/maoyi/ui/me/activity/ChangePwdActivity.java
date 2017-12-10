package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.ui.me.contract.ChangePwdContract;
import com.yizhisha.maoyi.ui.me.presenter.ChangePwdPresenter;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class ChangePwdActivity extends BaseActivity<ChangePwdPresenter> implements
        ChangePwdContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.old_pwd_et)
    ClearEditText mEtOldPwd;
    @Bind(R.id.new_pwd_et)
    ClearEditText mEtNewPwd;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(ChangePwdActivity.this);
            }
        });
    }
    @Override
    protected void initView() {

    }
    @Override
    public void changePwdSuccess(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    @OnClick({R.id.save_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.save_btn:
                String oldPwd=mEtOldPwd.getText().toString().trim();
                String newPwd=mEtNewPwd.getText().toString().trim();
                if(!checkInput(oldPwd,newPwd)){
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("uid", String.valueOf(AppConstant.UID));
                map.put("password1",oldPwd);
                map.put("password2",newPwd);
                mPresenter.changePwd(map);
                break;
        }
    }
    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String oldpwd,String pwd) {

        if (oldpwd == null || oldpwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入原登录密码");
            return false;
        }
        if (pwd == null || pwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入新的登录密码");
            return false;
        }
        return true;
    }
}
