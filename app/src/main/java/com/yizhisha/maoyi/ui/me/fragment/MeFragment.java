package com.yizhisha.maoyi.ui.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.base.rx.RxBus;
import com.yizhisha.maoyi.bean.json.MeInfoBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.event.LoginEvent;
import com.yizhisha.maoyi.event.UserHeadEvent;
import com.yizhisha.maoyi.ui.MainActivity;
import com.yizhisha.maoyi.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.maoyi.ui.login.activity.RegisterActivity;
import com.yizhisha.maoyi.ui.me.activity.AboutUsActivity;
import com.yizhisha.maoyi.ui.me.activity.CouponsActivity;
import com.yizhisha.maoyi.ui.me.activity.MyCollectActivity;
import com.yizhisha.maoyi.ui.me.activity.MyFootprintActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderActivity;
import com.yizhisha.maoyi.ui.me.activity.MyRatingActivity;
import com.yizhisha.maoyi.ui.me.activity.NewActivity;
import com.yizhisha.maoyi.ui.me.activity.ReFundOrderActivity;
import com.yizhisha.maoyi.ui.me.activity.SettingActivity;
import com.yizhisha.maoyi.ui.me.contract.MeContract;
import com.yizhisha.maoyi.ui.me.presenter.MePresenter;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lan on 2017/9/22.
 */

public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View {


    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.userPhoto_iv)
    ImageView userPhotoIv;
    @Bind(R.id.userName_tv)
    TextView userNameTv;
    @Bind(R.id.unpaymentNum_iv)
    TextView unpaymentNumTv;
    @Bind(R.id.unshippingNum_iv)
    TextView unshippingNumTv;
    @Bind(R.id.unreceive_goodsNum_iv)
    TextView unreceiveGoodsNumTv;
    @Bind(R.id.finishNum_iv)
    TextView finishNumTv;
    @Bind(R.id.refundNum_iv)
    TextView refundNumTv;
    private Subscription subscription;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NewActivity.class);
            }
        });
        if(AppConstant.isLogin==true){
            mPresenter.loadHeadInfo(AppConstant.UID);
        }else{
            userNameTv.setText("毛衣商城感谢您的支持,请登录");
        }
        event();

    }
    @OnClick({R.id.myorder_rl, R.id.unpayment_ll, R.id.unshipping_ll, R.id.unreceive_goods_ll,
            R.id.finish_ll, R.id.refund_ll, R.id.setting_rl, R.id.coupon_rl, R.id.collect_rl,
            R.id.footprint_rl, R.id.myEvaluation_rl, R.id.aboutUs_rl, R.id.serviceHotline_rl})
    /**
     *
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.myorder_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt("INDEX", 0);
                startActivity(MyOrderActivity.class, bundle);
                break;
            case R.id.unpayment_ll:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle1 = new Bundle();
                bundle1.putInt("INDEX", 1);
                startActivity(MyOrderActivity.class, bundle1);
                break;
            case R.id.unshipping_ll:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putInt("INDEX", 2);
                startActivity(MyOrderActivity.class, bundle2);
                break;
            case R.id.unreceive_goods_ll:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle3 = new Bundle();
                bundle3.putInt("INDEX", 3);
                startActivity(MyOrderActivity.class, bundle3);
                break;
            case R.id.finish_ll:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle4 = new Bundle();
                bundle4.putInt("INDEX", 4);
                startActivity(MyOrderActivity.class, bundle4);
                break;
            case R.id.refund_ll:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(ReFundOrderActivity.class);
                break;
            case R.id.setting_rl:
               /* if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }*/
                startActivity(SettingActivity.class);
                break;
            case R.id.coupon_rl:
                startActivity(CouponsActivity.class);
                break;
            case R.id.collect_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyCollectActivity.class);
                break;
            case R.id.footprint_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyFootprintActivity.class);
                break;
            case R.id.myEvaluation_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyRatingActivity.class);
                break;
            case R.id.aboutUs_rl:
                startActivity(AboutUsActivity.class);
                break;

            case R.id.serviceHotline_rl:
                new NormalAlertDialog.Builder(activity)
                        .setBoolTitle(false)
                        .setContentText("确定拨打客服热线吗?")
                        .setLeftText("取消")
                        .setRightText("呼叫")
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "0769-81064588"));
                                startActivity(phoneIneten);
                                dialog.dismiss();
                            }
                        }).build().show();
                break;


        }
    }
    //判断是否登陆
    private NormalSelectionDialog dialog;
    private void showLoginDialog(){
        final List<String> mDatas1=new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog=new NormalSelectionDialog.Builder(activity)
                .setBoolTitle(true)
                .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                .setTitleHeight(55)
                .setItemHeight(45)
                .setItemTextColor(R.color.blue)
                .setItemTextSize(14)
                .setItemWidth(0.95f)
                .setCancleButtonText("取消")
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position){
                            case 0:
                                startActivity(LoginFragmentActivity.class);
                                break;
                            case 1:
                                startActivity(RegisterActivity.class);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setTouchOutside(true)
                .build();
        dialog.setData(mDatas1);
    }
    @Override
    public void loadHeadSuccess(MeInfoBean info) {
        AppConstant.meInfoBean=info;
        userNameTv.setText(info.getNickname());
        int[] count=info.getOrderCount();
        for(int i=0;i<count.length;i++){
            if(i==0){
                if(count[i]==0){
                    unpaymentNumTv.setVisibility(View.GONE);
                }else{
                    unpaymentNumTv.setVisibility(View.VISIBLE);
                    unpaymentNumTv.setText(count[i]+"");
                }
            }else if(i==1){
                if(count[i]==0){
                    unshippingNumTv.setVisibility(View.GONE);
                }else{
                    unshippingNumTv.setVisibility(View.VISIBLE);
                    unshippingNumTv.setText(count[i]+"");
                }
            }else if(i==2){
                if(count[i]==0){
                    unreceiveGoodsNumTv.setVisibility(View.GONE);
                }else{
                    unreceiveGoodsNumTv.setVisibility(View.VISIBLE);
                    unreceiveGoodsNumTv.setText(count[i]+"");
                }
            }else if(i==3){
                if(count[i]==0){
                    finishNumTv.setVisibility(View.GONE);
                }else{
                    finishNumTv.setVisibility(View.VISIBLE);
                    finishNumTv.setText(count[i]+"");
                }
            }else if(i==4){
                if(count[i]==0){
                    refundNumTv.setVisibility(View.GONE);
                }else{
                    refundNumTv.setVisibility(View.VISIBLE);
                    refundNumTv.setText(count[i]+"");
                }
            }
        }
        GlideUtil.getInstance().LoadContextCircleBitmap(activity, AppConstant.HEAD_IMG_URL+info.getAvatar(),userPhotoIv ,
                R.drawable.icon_head_normal, R.drawable.icon_head_normal);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
    private void event(){
        subscription= RxBus.$().toObservable(Object.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object event) {
                        if (event instanceof UserHeadEvent) {
                            UserHeadEvent headEvent = (UserHeadEvent) event;
                            GlideUtil.getInstance().LoadContextCircleBitmap(activity, AppConstant.HEAD_IMG_URL+headEvent.getAvatar(), userPhotoIv,
                                    R.drawable.icon_head_normal, R.drawable.icon_head_normal);
                        }
                        else if(event instanceof LoginEvent){
                            mPresenter.loadHeadInfo(AppConstant.UID);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 103:
                if(AppConstant.meInfoBean==null){
                    userPhotoIv.setImageResource(R.drawable.icon_head_normal);
                    userNameTv.setText("毛衣商城感谢您的支持,请登录");
                }
                break;

            default:
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
