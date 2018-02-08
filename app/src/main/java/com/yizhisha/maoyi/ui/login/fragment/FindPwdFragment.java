package com.yizhisha.maoyi.ui.login.fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.WechatBean;
import com.yizhisha.maoyi.bean.json.WechatInfoBean;
import com.yizhisha.maoyi.ui.login.contract.LoginContract;
import com.yizhisha.maoyi.ui.login.presenter.LoginPresenter;
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

public class FindPwdFragment extends BaseFragment<LoginPresenter> implements LoginContract.View{

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.getcode_findpwd_tv)
    TextView mTvGetCode;
    @Bind(R.id.account_findpwd_et)
    ClearEditText mEtAccount;
    @Bind(R.id.code_findpwd_et)
    EditText mEtCode;
    @Bind(R.id.setpwd_findpwd_et)
    ClearEditText mEtSetPwd;
    @Bind(R.id.isShow_setpwd_iv)
    ImageView mIvIsShowSetPwd;
    private boolean isHidden1 = true;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_findpwd;
    }

    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void loginSuccess(RequestStatusBean info) {

    }

    @Override
    public void weChatLoginSuccess(RequestStatusBean bean) {

    }

    @Override
    public void registerSuccess(String info) {

    }

    @Override
    public void findPwdSuccess(String info) {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void getCodeSuccess(String info) {
        CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(mTvGetCode, 60000, 1000);
        mCountDownTimerUtils.start();
        ToastUtil.showShortToast(info);
    }

    @Override
    public void loadWeChatData(WechatBean wechatBean) {

    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }

    @Override
    public void weChatLogin(String info) {
        ToastUtil.showbottomShortToast(info);
    }

    @Override
    public void bindWeChatSuccess(String info) {

    }

    @Override
    public void loadWeChatInfo(WechatInfoBean bean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String accout,String code,String pwd) {
        // 账号为空时提示
        if (accout == null || accout.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入你的手机号码");
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

    @OnClick({R.id.getcode_findpwd_tv,R.id.sure_findpwd_btn,R.id.isShow_setpwd_iv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_findpwd_tv:
                String phone=mEtAccount.getText().toString().trim();
                if (phone == null || phone.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("mobile",mEtAccount.getText().toString().trim());
                mPresenter.getCode(map);
                break;
            case R.id.sure_findpwd_btn:
                if(!checkInput(mEtAccount.getText().toString(),mEtCode.getText().toString(),
                        mEtSetPwd.getText().toString()
                )){
                    return;
                }
                Map<String,String> map1=new HashMap<>();
                map1.put("mobile",mEtAccount.getText().toString().trim());
                map1.put("password",mEtSetPwd.getText().toString().trim());
                map1.put("mobilecode",mEtCode.getText().toString().trim());
                mPresenter.findPwd(map1);
                break;
            case R.id.isShow_setpwd_iv:
                if (isHidden1) {
                    mIvIsShowSetPwd.setImageResource(R.drawable.icon_view);
                    mEtSetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mIvIsShowSetPwd.setImageResource(R.drawable.icon_close);
                    mEtSetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden1 = !isHidden1;
                break;

        }
    }
}
