package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.ActivityManager;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.ui.me.contract.AddAddressContract;
import com.yizhisha.maoyi.ui.me.presenter.AddAddressPresenter;
import com.yizhisha.maoyi.utils.CheckUtils;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements AddAddressContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consignee_address_et)
    EditText consigneeAddressEt;

    @Bind(R.id.phone_address_et)
    EditText phoneAddressEt;

    @Bind(R.id.area_address_tv)
    TextView areaAddressTv;

    @Bind(R.id.detailaddress_address_et)
    EditText detailaddressAddressEt;

    private int type=0;

    private int addressId=0;

    private CityPickerView mCityPickerView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initToolBar() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt("TYPE");
        if(type==1){
            toolbar.setTitle("编辑收货地址");
            GoodsListBean.Address address= (GoodsListBean.Address) bundle.getSerializable("ADDRESS");
            consigneeAddressEt.setText(address.getLinkman());
            phoneAddressEt.setText(address.getMobile());
            areaAddressTv.setText(address.getArea_app());
            detailaddressAddressEt.setText(address.getAddress());
        }
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(AddAddressActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        mCityPickerView = new CityPickerView(this);
    }

    @Override
    public void addAddressSuccess(RequestStatusBean data) {

    }

    @Override
    public void loadFail(String msg) {

    }
    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String consignee,String phone,String detailaddress) {
        // 账号为空时提示
        if (consignee == null || consignee.trim().equals("")) {
            ToastUtil.showCenterShortToast("请填写收件人");
            return false;
        }
        if (phone == null || phone.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入手机号码");
            return false;
        }

        if (!CheckUtils.isMobileNO(phone)) {
            ToastUtil.showCenterShortToast("请输入正确的手机号码");
            return false;
        }

        if (detailaddress == null || detailaddress.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入详细的收货地址");
            return false;
        }

        return true;
    }
    public void selectCity(){
        // 设置点击外部是否消失
//        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
//        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener(){
            @Override
            public void onCitySelect(String str) {
                // 一起获取
                areaAddressTv.setText(str);
            }
        });
        mCityPickerView.show();
    }
    @OnClick({R.id.sava_address_tv,R.id.addaddress_ll3})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sava_address_tv:
                if(!checkInput(consigneeAddressEt.getText().toString(),phoneAddressEt.getText().toString(),detailaddressAddressEt.getText().toString())){
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("uid",String.valueOf(AppConstant.UID));
                if(type==1){
                    map.put("id",String.valueOf(addressId));
                }
                map.put("index",String.valueOf(0));
                map.put("linkman",consigneeAddressEt.getText().toString());
                map.put("area_app",areaAddressTv.getText().toString());
                map.put("mobile",phoneAddressEt.getText().toString());
                map.put("address",detailaddressAddressEt.getText().toString());
                mPresenter.addAddress(map);
                break;
            case R.id.addaddress_ll3:
                selectCity();
                break;
        }
    }
}
