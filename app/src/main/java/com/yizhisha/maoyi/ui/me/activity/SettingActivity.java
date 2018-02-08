package com.yizhisha.maoyi.ui.me.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.utils.CacheDataManager;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.SharedPreferencesUtil;
import com.yizhisha.maoyi.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;
import rx.annotations.Beta;

public class SettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.cache_tv)
    TextView cacheTv;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    protected void initToolBar(){
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(SettingActivity.this);
            }
        });
    }
    @Override
    protected void initView(){
        try {
            cacheTv.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case 0:
                    ToastUtil.showShortToast("清理完成");
                    try {

                        cacheTv.setText(CacheDataManager.getTotalCacheSize(SettingActivity.this));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

            }

        };

    };
    class clearCache implements Runnable {

        @Override

        public void run() {

            try {

                CacheDataManager.clearAllCache(SettingActivity.this);

                Thread.sleep(3000);

                if (CacheDataManager.getTotalCacheSize(SettingActivity.this).startsWith("0")) {

                    handler.sendEmptyMessage(0);

                }

            } catch (Exception e) {

                return;

            }

        }

    }


    @OnClick({R.id.personalInfo_rl,R.id.changePwd_rl,R.id.changePhone_rl,R.id.goodaddress_rl,R.id.exit_btn,R.id.clean_cache_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personalInfo_rl:
                startActivity(PersonalInfoActivity.class);
                break;
            case R.id.changePwd_rl:
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.changePhone_rl:
                startActivity(ChangePhoneActivity.class);
                break;
            case R.id.goodaddress_rl:
                startActivity(MyAddressActivity.class);
                break;
            case R.id.exit_btn:
                SharedPreferencesUtil.removeValue(this,"ISLOGIN");
                SharedPreferencesUtil.removeValue(this,"UID");
                AppConstant.isLogin=false;
                AppConstant.meInfoBean=null;
                AppConstant.UID=0;
                setResult(103);
                finish_Activity(this);
                break;
            case R.id.clean_cache_rl:
                new Thread(new clearCache()).start();
                break;
        }
    }
}
