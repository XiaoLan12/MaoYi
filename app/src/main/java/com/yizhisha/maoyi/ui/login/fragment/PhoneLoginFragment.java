package com.yizhisha.maoyi.ui.login.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.login.contract.PhoneLoginContract;
import com.yizhisha.maoyi.ui.login.presenter.PhoneLoginPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.CountDownTimerUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/2.
 */

public class PhoneLoginFragment extends BaseFragment<PhoneLoginPresenter> implements PhoneLoginContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.account_phonelogin_et)
    ClearEditText accountPhoneloginEt;
    @Bind(R.id.code_phonelogin_et)
    ClearEditText codePhoneloginEt;
    @Bind(R.id.getcode_phonelogin_tv)
    TextView getcodePhoneloginTv;
    @Bind(R.id.sure_phonelogin_btn)
    Button surePhoneloginBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phonelogin;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void loginSuccess(LoginBean info) {
        ToastUtil.showShortToast(info.getInfo());
    }

    @Override
    public void getCodeSuccess(RequestStatusBean info) {
        CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(getcodePhoneloginTv, 60000, 1000);
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

    @OnClick({R.id.getcode_phonelogin_tv,R.id.sure_phonelogin_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_phonelogin_tv:
                String phone1=accountPhoneloginEt.getText().toString().trim();
                if (phone1 == null || phone1.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone1)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }

                Map<String,String> map1=new HashMap<>();
                map1.put("mobile",accountPhoneloginEt.getText().toString().trim());
                map1.put("type","login");
                mPresenter.getCode(map1);
                break;
            case R.id.sure_phonelogin_btn:
                String phone=accountPhoneloginEt.getText().toString().trim();
                if(phone==null||phone.equals("")){
                    ToastUtil.showShortToast("请输入手机号");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }
                String pwd=codePhoneloginEt.getText().toString().trim();
                if (pwd==null||pwd.equals("")){
                    ToastUtil.showShortToast("请输入验证码");
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("mobile",phone);
                map.put("mobilecode",pwd);
                mPresenter.login(map);
                break;
        }
    }
}
