package com.yizhisha.maoyi.ui.login.fragment;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.login.contract.LoginContract;
import com.yizhisha.maoyi.ui.login.presenter.LoginPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/2.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.account_login_et)
    ClearEditText mEtAccount;
    @Bind(R.id.pwd_login_et)
    ClearEditText mEtPwd;
    @Bind(R.id.isShow_pwd_iv)
    ImageView mIvIsShowPwd;
    private boolean isHidden = true;
    public  switchFragmentListener switchFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //对传递进来的Activity进行接口转换
        if(getActivity() instanceof switchFragmentListener){
            switchFragmentListener = ((switchFragmentListener)getActivity());
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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

    public interface switchFragmentListener{
        void switchFragment(int index);
    }
    @OnClick({R.id.findpwd_tv,R.id.register_tv,R.id.phone_login_tv,R.id.login_btn,
            R.id.isShow_pwd_iv,R.id.weixin_login_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.findpwd_tv:
                switchFragmentListener.switchFragment(1);
                break;
            case R.id.register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.phone_login_tv:
                switchFragmentListener.switchFragment(2);
                break;
            case R.id.login_btn:
                    String phone=mEtAccount.getText().toString().trim();
                    if(phone==null||phone.equals("")){
                        ToastUtil.showShortToast("请输入手机号");
                        return;
                    }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }
                String pwd=mEtPwd.getText().toString().trim();
                if (pwd==null||pwd.equals("")){
                    ToastUtil.showShortToast("请输入密码");
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("mobile",phone);
                map.put("password",pwd);
                 mPresenter.login(map);
                break;
            case R.id.isShow_pwd_iv:
                if (isHidden) {
                    mIvIsShowPwd.setImageResource(R.drawable.icon_view);
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mIvIsShowPwd.setImageResource(R.drawable.icon_close);
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                break;
            case R.id.weixin_login_tv:

                break;
        }
    }
}
