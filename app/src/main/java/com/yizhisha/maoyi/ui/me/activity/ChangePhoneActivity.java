package com.yizhisha.maoyi.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.BindContract;
import com.yizhisha.maoyi.ui.me.presenter.BindPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.CountDownTimerUtil;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class ChangePhoneActivity extends BaseActivity<BindPresenter> implements BindContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Bind(R.id.bind_tv)
    TextView bindTv;
    @Bind(R.id.bind_phone_tv)
    TextView bindPhoneTv;
    @Bind(R.id.phone_chagephone_et)
    ClearEditText phoneChagephoneEt;
    @Bind(R.id.getcode_chagephone_et)
    EditText getcodeChagephoneEt;
    @Bind(R.id.getcode_chagephone_tv)
    TextView getcodeChagephoneTv;
    @Bind(R.id.sava_chagephone_btn)
    Button mBtnSava;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_phone;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(ChangePhoneActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        mBtnSava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneChagephoneEt.getText().toString().trim();
                String code = getcodeChagephoneEt.getText().toString().trim();
                if (!checkInput(phone, code)) {
                    return;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("uid", String.valueOf(AppConstant.UID));
                map.put("mobile", phone);
                map.put("mobilecode", code);
                mPresenter.bing(map);
            }
        });
        getcodeChagephoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneChagephoneEt.getText().toString().trim();
                if (phone == null || phone.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }

                Map<String,String> map=new HashMap<>();
                map.put("uid", String.valueOf(AppConstant.UID));
                map.put("mobile",phone);
                mPresenter.getCode(map);
            }
        });
        mPresenter.loadBindPhone(AppConstant.UID);
    }

    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String accout,String code) {
        // 账号为空时提示
        if (accout == null || accout.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入手机号码");
            return false;
        }
        if (!CheckUtils.isMobileNO(accout)) {
            ToastUtil.showCenterShortToast("请输入正确的手机号码");
            return false;
        }
        if (code == null || code.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入验证码");
            return false;
        }
        return true;
    }

    @Override
    public void bindSuccess(RequestStatusBean msg) {
        if(msg.getStatus().equals("y")) {
            bindTv.setText("已绑定手机号码:");
            bindPhoneTv.setText(phoneChagephoneEt.getText().toString());
        }else{
            getcodeChagephoneTv.setTextColor(RescourseUtil.getColor(R.color.gray));
            getcodeChagephoneTv.setEnabled(false);
        }
        ToastUtil.showShortToast(msg.getInfo());
        finish();
    }

    @Override
    public void getCodeSuccess(String info) {
        CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(getcodeChagephoneTv, 60000, 1000);
        mCountDownTimerUtils.start();
        ToastUtil.showShortToast(info);
    }

    @Override
    public void loadBindPhone(String msg) {
        bindTv.setText("已绑定手机号码:");
        bindPhoneTv.setText(msg);
    }

    @Override
    public void loadBindPhoneFail(String msg) {
        bindTv.setText(msg);
        bindTv.setTextColor(RescourseUtil.getColor(R.color.common_color));
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
}