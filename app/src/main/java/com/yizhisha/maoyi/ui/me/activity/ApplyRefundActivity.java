package com.yizhisha.maoyi.ui.me.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.ChoosePhotoListAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.ui.me.contract.ApplyRefundContract;
import com.yizhisha.maoyi.ui.me.presenter.ApplyRefundPresenter;
import com.yizhisha.maoyi.utils.ImageUtils;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ApplyRefundActivity extends BaseActivity<ApplyRefundPresenter> implements
        ApplyRefundContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.refundType_tv)
    TextView refundTypeTv;
    @Bind(R.id.refundCause_tv)
    TextView refundCauseTv;
    @Bind(R.id.refundCause_iv)
    ImageView refundCauseIv;
    @Bind(R.id.refundAmount_tv)
    EditText refundAmountTv;
    @Bind(R.id.refundExplain_et)
    EditText refundExplainEt;

    private LoadingDialog mLoadingDialog;
    private ChoosePhotoListAdapter mAdapter;
    private ArrayList<String> path = new ArrayList<String>();
    private ArrayList<String> loadPath = new ArrayList<String>();
    private String refundNo = "";
    private int mType;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(ApplyRefundActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            refundNo = bundle.getString("ORDERNO", "");
            mType=bundle.getInt("TYPE",1);
        }
        if(mType==1){
            refundTypeTv.setText("仅退款");
        }else{
            refundTypeTv.setText("退货退款");
        }
        initAdapter();

    }

    private void initAdapter() {
        path.add("selectpic");
        mAdapter = new ChoosePhotoListAdapter(path);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
              /*  Bundle bundle=new Bundle();
                bundle.putSerializable("list", (Serializable) path);
                bundle.putInt("number", i);
                start_Activity(ShowImageActivity.class,bundle);*/
                String imgs = path.get(position);
                if ("selectpic".equals(imgs)) {
                    if (path.contains("selectpic")) {
                        path.remove("selectpic");
                    }
                    showPhotoPicker();
                } else {
                    if (path.contains("selectpic")) {
                        path.remove("selectpic");
                    }
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(ApplyRefundActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(path);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
                switch (view.getId()) {
                    case R.id.delete_img_iv:
                        path.remove(i);
                        if (!path.contains("selectpic") && path.size() < 5) {
                            path.add("selectpic");
                        }
                        mAdapter.setNewData(path);
                        break;
                }
            }
        });
    }
    private void uploadRefund(String photo){
        Map<String,String> body=new HashMap<>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("orderno",refundNo);
        body.put("reason",refundCauseTv.getText().toString().trim());
        body.put("detail",refundExplainEt.getText().toString().trim());
        body.put("money",refundAmountTv.getText().toString().trim());
        body.put("pic",photo);
        body.put("type",String.valueOf(mType));
        mPresenter.addRefund(body);
    }

    private void uploadPic(){
        if (path.contains("selectpic")) {
            path.remove("selectpic");
        }
        for(int i=0;i<path.size();i++){
            try {
                String pic= ImageUtils.bitmapToPath(path.get(i));
                File file = new File(pic);
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("refundPic", file.getName(), requestFile);
                mPresenter.addPic(body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                        finish_Activity(ApplyRefundActivity.this);
                        dialog.dismiss();
                    }
                }).build().show();
    }
    @Override
    public void addPicSuccess(String img) {
        loadPath.add(img);
        if(loadPath.size()==path.size()){
            StringBuilder str=new StringBuilder();
            for(int i=0;i<loadPath.size();i++){
                str.append(loadPath.get(i)).append(",");
            }
            uploadRefund(str.substring(0,str.length()-1).toString());
        }
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
    private void showPhotoPicker() {
        performCodeWithPermission("软件更新需要您提供浏览存储的权限", new PermissionCallback() {
            @Override
            public void hasPermission() {
                PhotoPickerIntent intent = new PhotoPickerIntent(ApplyRefundActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(5); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(path); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }
    @OnClick({R.id.refundCause_rl,R.id.submit_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.refundCause_rl:
                final List<String> headData=new ArrayList<>();
                headData.add("多拍了,不想要");
                headData.add("空包裹");
                headData.add("快递/物流一直未送到");
                headData.add("快递/物流没有跟踪记录");
                headData.add("货物破损已拒收");
                headData.add("其他");
                NormalSelectionDialog chaHeaddialog=new NormalSelectionDialog.Builder(this)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.common_h1)
                        .setItemTextSize(14)
                        .setItemWidth(1f)
                        .setBoolCancle(false)
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                refundCauseTv.setText(headData.get(position));
                                dialog.dismiss();

                            }
                        }).setTouchOutside(true)
                        .build();

                chaHeaddialog.setData(headData);
                chaHeaddialog.show();
                break;
            case R.id.submit_btn:
                if(refundCauseTv.getText().equals("")){
                    ToastUtil.showbottomShortToast("请选择退款原因");
                    return;
                }
                if(refundAmountTv.getText().equals("")){
                    ToastUtil.showbottomShortToast("请输入退款金额");
                    return;
                }
                showLoading();

                if(path.size()>1) {
                    loadPath.clear();
                    uploadPic();
                }else{
                    uploadRefund("");
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:

                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    path.clear();
                    path.addAll(list);
                    if (!path.contains("selectpic") && path.size() < 5) {
                        path.add("selectpic");
                    }
                    mAdapter.setNewData(path);
                    //loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    path.clear();
                    path.addAll(ListExtra);
                    if (!path.contains("selectpic") && path.size() < 5) {
                        path.add("selectpic");
                    }
                    mAdapter.setNewData(path);
                    break;
            }
        } else {
            if (!path.contains("selectpic") && path.size() < 5) {
                path.add("selectpic");
            }
            mAdapter.setNewData(path);
        }
    }

}
