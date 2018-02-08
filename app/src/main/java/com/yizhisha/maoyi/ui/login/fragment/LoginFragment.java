package com.yizhisha.maoyi.ui.login.fragment;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.base.rx.RxBus;
import com.yizhisha.maoyi.bean.json.LoginBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.WechatBean;
import com.yizhisha.maoyi.bean.json.WechatInfoBean;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.popupwindow.LoginWithWeiPopuwindow;
import com.yizhisha.maoyi.event.LoginEvent;
import com.yizhisha.maoyi.event.WeChatEvent;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.login.contract.LoginContract;
import com.yizhisha.maoyi.ui.login.presenter.LoginPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.SharedPreferencesUtil;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
    private IWXAPI api;
    public  switchFragmentListener switchFragmentListener;
    LoginWithWeiPopuwindow loginWithWeiPopuwindow;
    //是否绑定微信
    private boolean isBangWei=false;
    private int uid;
    private String oppenid="";
    private String token="";

    private Subscription subscription;
    private LoadingDialog mLoadingDialog;

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
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        api = WXAPIFactory.createWXAPI(getActivity(), AppConstant.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(AppConstant.WEIXIN_APP_ID);
        event();
    }
    @Override
    public void loginSuccess(RequestStatusBean bean) {
        if(isBangWei){
            //缓存暂时的uid
            uid=bean.getUid();
            String url="https://api.weixin.qq.com/sns/userinfo?access_token="
                    + token
                    + "&openid="
                    + oppenid;
            mPresenter.loadWeChatInfo(url);

        }else{
            SharedPreferencesUtil.putValue(activity,"ISLOGIN",true);
            AppConstant.isLogin=true;
            AppConstant.UID=bean.getUid();
            SharedPreferencesUtil.putValue(activity,"UID",AppConstant.UID);
            RxBus.$().postEvent(new LoginEvent());
            activity.finish();
        }
    }

    @Override
    public void weChatLoginSuccess(RequestStatusBean bean) {
        SharedPreferencesUtil.putValue(activity,"ISLOGIN",true);
        AppConstant.isLogin=true;
        AppConstant.UID=bean.getUid();
        SharedPreferencesUtil.putValue(activity,"UID",AppConstant.UID);
        RxBus.$().postEvent(new LoginEvent());
        activity.finish();
    }

    @Override
    public void registerSuccess(String info) {

    }

    @Override
    public void findPwdSuccess(String info) {

    }

    @Override
    public void getCodeSuccess(String info) {

    }

    @Override
    public void loadWeChatData(WechatBean wechatBean) {
        Map<String,String> map=new HashMap<>();
        map.put("openid",wechatBean.getOpenid());
        oppenid=wechatBean.getOpenid();
        token=wechatBean.getAccess_token();
        mPresenter.weChatLogin(map);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);

    }

    @Override
    public void weChatLogin(String info) {
        if(loginWithWeiPopuwindow==null){
            loginWithWeiPopuwindow=new LoginWithWeiPopuwindow(getActivity());
            //去注册
            loginWithWeiPopuwindow.setOnContinueClickListener(new LoginWithWeiPopuwindow.OnContinueClickListener() {
                @Override
                public void onContinueClickListener() {
                    startActivity(RegisterActivity.class);
                }
            });
            //绑定微信
            loginWithWeiPopuwindow.setOnBackIndexClickListener(new LoginWithWeiPopuwindow.OnBackIndexClickListener(){
                @Override
                public void onBackIndexClickListener() {
                    isBangWei=true;
//                    ToastUtil.showShortToast("绑定");
                }
            });
            loginWithWeiPopuwindow.showAtLocation(mIvIsShowPwd, Gravity.CENTER, 0, 0);
        }else{


            loginWithWeiPopuwindow.showAtLocation(mIvIsShowPwd, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void bindWeChatSuccess(String info) {
        SharedPreferencesUtil.putValue(activity,"ISLOGIN",true);
        AppConstant.isLogin=true;
        AppConstant.UID=uid;
        SharedPreferencesUtil.putValue(activity,"UID",AppConstant.UID);
        RxBus.$().postEvent(new LoginEvent());
        activity.finish();
    }

    @Override
    public void loadWeChatInfo(WechatInfoBean bean) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(uid));
        map.put("openid",oppenid);
        map.put("nickname",bean.getNickname());
        mPresenter.bindWeChat(map);
    }

    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(activity,"正在登录...",false);
        mLoadingDialog.show();
    }
    @Override
    public void hideLoading() {
        if(mLoadingDialog!=null){
            mLoadingDialog.cancelDialog();
        }
    }
    private void event(){
        subscription= RxBus.$().toObservable(WeChatEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatEvent>() {
                    @Override
                    public void call(WeChatEvent event) {
                        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                                + AppConstant.WEIXIN_APP_ID
                                + "&secret="
                                + AppConstant.APP_SECRET
                                + "&code="
                                + event.getCode()
                                + "&grant_type=authorization_code";
                        mPresenter.loadWeChatData(url);
                    }
                });
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
                if (!api.isWXAppInstalled()) {
                    ToastUtil.showbottomShortToast("您还未安装微信客户端");
                    return;
                }
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "taosha_wx_login";
                api.sendReq(req);
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
