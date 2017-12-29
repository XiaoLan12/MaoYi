package com.yizhisha.maoyi.ui.login.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.login.contract.RegisterContract;
import com.yizhisha.maoyi.ui.login.presenter.RegisterPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.CountDownTimerUtil;
import com.yizhisha.maoyi.utils.IPUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/2.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.account_register_et)
    ClearEditText accountRegisterEt;
    @Bind(R.id.code_register_et)
    EditText codeRegisterEt;
    @Bind(R.id.getcode_register_tv)
    TextView getcodeRegisterTv;
    @Bind(R.id.setpwd_register_et)
    ClearEditText setpwdRegisterEt;
    @Bind(R.id.setpwd_register_iv)
    ImageView setpwdRegisterIv;
    @Bind(R.id.sure_findpwd_btn)
    Button sureFindpwdBtn;
    @Bind(R.id.terms_cb)
    CheckBox termsCb;
    @Bind(R.id.terms_register_tv)
    TextView termsRegisterTv;
    @Bind(R.id.login_register_tv)
    TextView loginRegisterTv;

    private boolean isHidden1 = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(RegisterActivity.this);
            }
        });

    }

    @Override
    protected void initView() {
//        Log.e("TTT", IPUtil.getIPAddress(RegisterActivity.this));
    }

    @Override
    public void registerSuccess(RequestStatusBean info) {
        ToastUtil.showShortToast(info.getInfo());
        finish_Activity(RegisterActivity.this);

    }

    @Override
    public void getCodeSuccess(RequestStatusBean info) {
        CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(getcodeRegisterTv, 60000, 1000);
        mCountDownTimerUtils.start();
        ToastUtil.showShortToast(info.getInfo());
    }

    @Override
    public void loadFail(String msg) {

        ToastUtil.showShortToast(msg);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.getcode_register_tv,R.id.login_register_tv,R.id.setpwd_register_iv,R.id.sure_findpwd_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_register_tv:
                String phone=accountRegisterEt.getText().toString().trim();
                if (phone == null || phone.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }

                Map<String,String> map=new HashMap<>();
                map.put("mobile",accountRegisterEt.getText().toString().trim());
                map.put("type","reg");
                mPresenter.getCode(map);
                break;
            case R.id.login_register_tv:
                startActivity(LoginFragmentActivity.class);
                break;
            case R.id.setpwd_register_iv:
                if (isHidden1) {
                    setpwdRegisterIv.setImageResource(R.drawable.icon_view);
                    setpwdRegisterEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    setpwdRegisterIv.setImageResource(R.drawable.icon_close);
                    setpwdRegisterEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden1 = !isHidden1;
                break;
            case R.id.sure_findpwd_btn:
                String phone1=accountRegisterEt.getText().toString().trim();
                if (phone1 == null || phone1.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone1)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }
                String code=codeRegisterEt.getText().toString().trim();
                if (code == null || code.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入验证码");
                    return;
                }
                String pwd=setpwdRegisterEt.getText().toString().trim();
                if (pwd == null || pwd.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入您的密码");
                    return;
                }
                if(pwd.length()<6||pwd.length()>20){
               ToastUtil.showCenterShortToast("请输入6~20位密码");
                    return;
                }
                Map<String,String> map1=new HashMap<>();
                map1.put("mobile",accountRegisterEt.getText().toString().trim());
                map1.put("mobilecode",code);
                map1.put("password",pwd);
                map1.put("utype","1");
                map1.put("regip", IPUtil.getIPAddress(RegisterActivity.this));
                mPresenter.register(map1);
                break;

        }
    }
}
