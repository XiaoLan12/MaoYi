package com.yizhisha.maoyi.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.ui.me.contract.RefundGoodContract;
import com.yizhisha.maoyi.ui.me.presenter.RefundGoodPresenter;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundGoodActivity extends BaseActivity<RefundGoodPresenter> implements RefundGoodContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.refundLogistics_tv)
    TextView refundLogisticsTv;
    @Bind(R.id.refundLogisticse_rl)
    RelativeLayout refundLogisticseRl;
    @Bind(R.id.logisticsNum_tv)
    EditText logisticsNumTv;
    @Bind(R.id.phone_tv)
    EditText phoneTv;
    @Bind(R.id.refundExplain_et)
    EditText refundExplainEt;
    @Bind(R.id.submit_btn)
    Button submitBtn;

    private LoadingDialog mLoadingDialog;
    private int refundId;
    private int express_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_refund_good;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(RefundGoodActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            refundId=bundle.getInt("REFUNDID",1);
        }
        loadData();
    }
    private void upRefund(){
        Map<String,String> body=new HashMap<>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("express_no",logisticsNumTv.getText().toString().trim());
        body.put("detail",refundExplainEt.getText().toString().trim());
        body.put("express_id",String.valueOf(express_id));
        body.put("mobile",phoneTv.getText().toString().trim());
        body.put("id",String.valueOf(refundId));
        mPresenter.refund(body);
    }
    @Override
    public void addRefundSuccess(String data) {
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(data)
                .setSingleModel(true)
                .setSingleText("确认")
                .setSingleTextColor(R.color.blue)
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        finish_Activity(RefundGoodActivity.this);
                        dialog.dismiss();
                    }
                }).build().show();
    }
    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(this,"正在提交申请...",false);
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.cancelDialog();
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    private List<String> headData=new ArrayList<>();
    private void loadData(){
        headData=new ArrayList<>();
        headData.add("顺丰快递");
        headData.add("圆通快递");
        headData.add("中通快递");
        headData.add("申通快递");
        headData.add("韵达快递");
        headData.add("京东快递");
        headData.add("全峰快递");
        headData.add("邮政EMS");
        headData.add("安能快递");
        headData.add("百世快递");
        headData.add("速尔快递");
        headData.add("天天快递");
        headData.add("邮政包裹");
    }
    @OnClick({R.id.refundLogisticse_rl,R.id.submit_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refundLogisticse_rl:

                NormalSelectionDialog chaHeaddialog=new NormalSelectionDialog.Builder(this)
                        .setItemHeight(40)
                        .setItemTextColor(R.color.common_h1)
                        .setItemTextSize(14)
                        .setItemWidth(1f)
                        .setHeight(0.7f)
                        .setBoolCancle(false)
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                refundLogisticsTv.setText(headData.get(position));
                                express_id=position+1;
                                dialog.dismiss();

                            }
                        }).setTouchOutside(true)
                        .build();

                chaHeaddialog.setData(headData);
                chaHeaddialog.show();
                break;
            case R.id.submit_btn:
                if(refundLogisticsTv.getText().equals(" ")){
                    ToastUtil.showbottomShortToast("请选择物流公司");
                    return;
                }
                if(logisticsNumTv.getText().equals(" ")){
                    ToastUtil.showbottomShortToast("请填写物流单号");
                    return;
                }
                if(phoneTv.getText().equals(" ")){
                    ToastUtil.showbottomShortToast("请填写联系电话");
                    return;
                }
                if(phoneTv.getText().equals(" ")){
                    ToastUtil.showbottomShortToast("请填写退款说明");
                    return;
                }
                showLoading();
                //upRefund();
                break;
        }
    }
}
