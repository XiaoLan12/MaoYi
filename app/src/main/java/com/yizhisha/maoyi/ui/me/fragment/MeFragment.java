package com.yizhisha.maoyi.ui.me.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.MyRefundOrderAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.ui.me.activity.AboutUsActivity;
import com.yizhisha.maoyi.ui.me.activity.CouponsActivity;
import com.yizhisha.maoyi.ui.me.activity.MyCollectActivity;
import com.yizhisha.maoyi.ui.me.activity.MyFootprintActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderActivity;
import com.yizhisha.maoyi.ui.me.activity.MyRatingActivity;
import com.yizhisha.maoyi.ui.me.activity.ReFundOrderActivity;
import com.yizhisha.maoyi.ui.me.activity.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by lan on 2017/9/22.
 */

public class MeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }
    @Override
    protected void initView() {

    }
    @OnClick({R.id.myorder_rl,R.id.unpayment_ll,R.id.unshipping_ll,R.id.unreceive_goods_ll,
            R.id.finish_ll,R.id.refund_ll,R.id.setting_rl,R.id.coupon_rl,R.id.collect_rl,
            R.id.footprint_rl,R.id.myEvaluation_rl,R.id.aboutUs_rl,R.id.serviceHotline_rl})
    /**
     *
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.myorder_rl:
                Bundle bundle=new Bundle();
                bundle.putInt("INDEX",0);
                startActivity(MyOrderActivity.class,bundle);
                break;
            case R.id.unpayment_ll:
                Bundle bundle1=new Bundle();
                bundle1.putInt("INDEX",1);
                startActivity(MyOrderActivity.class,bundle1);
                break;
            case R.id.unshipping_ll:
                Bundle bundle2=new Bundle();
                bundle2.putInt("INDEX",2);
                startActivity(MyOrderActivity.class,bundle2);
                break;
            case R.id.unreceive_goods_ll:
                Bundle bundle3=new Bundle();
                bundle3.putInt("INDEX",3);
                startActivity(MyOrderActivity.class,bundle3);
                break;
            case R.id.finish_ll:
                Bundle bundle4=new Bundle();
                bundle4.putInt("INDEX",4);
                startActivity(MyOrderActivity.class,bundle4);
                break;
            case R.id.refund_ll:
                startActivity(ReFundOrderActivity.class);
                break;
            case R.id.setting_rl:
                startActivity(SettingActivity.class);
                break;
            case R.id.coupon_rl:
                startActivity(CouponsActivity.class);
                break;
            case R.id.collect_rl:
                startActivity(MyCollectActivity.class);
                break;
            case R.id.footprint_rl:
                startActivity(MyFootprintActivity.class);
                break;
            case R.id.myEvaluation_rl:
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
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "08979589578"));
                                startActivity(phoneIneten);
                                dialog.dismiss();
                            }
                        }).build().show();
                break;


        }
    }
}
