package com.yizhisha.maoyi.ui.me.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.ui.me.activity.MyOrderActivity;

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
            R.id.finish_ll,R.id.refund_ll})
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

                break;

        }
    }
}
