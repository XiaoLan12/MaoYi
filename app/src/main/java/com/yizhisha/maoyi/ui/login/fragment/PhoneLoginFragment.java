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
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import butterknife.Bind;

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

    }

    @Override
    public void getCodeSuccess(RequestStatusBean info) {

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

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
