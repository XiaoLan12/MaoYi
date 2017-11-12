package com.yizhisha.maoyi.ui.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.NormalSelectionDialog;
import com.yizhisha.maoyi.ui.ClipHeaderActivity;
import com.yizhisha.maoyi.ui.me.contract.PersonalInfoContract;
import com.yizhisha.maoyi.ui.me.presenter.PersonalInfoPresenter;
import com.yizhisha.maoyi.utils.ImageUtils;
import com.yizhisha.maoyi.utils.RescourseUtil;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.io.File;
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
import qiu.niorgai.StatusBarCompat;

public class PersonalInfoActivity extends BaseActivity<PersonalInfoPresenter> implements PersonalInfoContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.headIv)
    ImageView headIv;
    @Bind(R.id.nickname_tv)
    TextView nicknameTv;
    @Bind(R.id.sex_tv)
    TextView sexTv;

    private final static int NICKNAME_REQUEST=200;
    public final static int NICKNAME_RESULT=201;

    //调用相机相册
    private static final int RESULT_CAPTURE = 100;
    private static final int RESULT_PICK = 101;
    private static final int CROP_PHOTO = 102;

    private File tempFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }
    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/ziyiApp/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }
    /**判断文件是否存在
     * @param dirPath
     * @return
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    @Override
    protected int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, RescourseUtil.getColor(R.color.common_divider_narrow));
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(PersonalInfoActivity.this);
            }
        });
    }

    @Override
    protected void initView() {

    }
    private void load(Map<String,String> map){
        mPresenter.changePersonalInfo(map);
    }
    @OnClick({R.id.nickname_rl, R.id.sex_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nickname_rl:
                startActivityForResult(ChangeNickNameActivity.class,NICKNAME_REQUEST);
                break;
            case R.id.sex_rl:
                final List<String> sexDatas = new ArrayList<>();
                sexDatas.add("男");
                sexDatas.add("女");
                NormalSelectionDialog dialog = new NormalSelectionDialog.Builder(this)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.7f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                sexTv.setText(sexDatas.get(position));
                                Map<String,String> map=new HashMap<>();
                                map.put("uid", String.valueOf(AppConstant.UID));
                                map.put("sex", sexTv.getText().toString());
                                load(map);
                                dialog.dismiss();
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog.setData(sexDatas);
                dialog.show();
                break;
        }
    }
    //打开照相机
    private void startCamera() {
        if (!tempFile.getParentFile().exists())tempFile.getParentFile().mkdirs();
        Uri imageUri = FileProvider.getUriForFile(this, "com.maoyi.takephoto.fileprovider", tempFile);//通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        if (intent.resolveActivity(this.getPackageManager()) != null) {//判断是否有相机应用
            startActivityForResult(intent,RESULT_CAPTURE);
        }else{
            ToastUtil.showShortToast("没有找到相机");
        }
    }

    //打开相册
    private void startAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), RESULT_PICK);
    }

    /**
     * 打开截图界面
     *
     * @param uri 原图的Uri
     */
    public void starCropPhoto(Uri uri) {

        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipHeaderActivity.class);
        intent.setData(uri);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, CROP_PHOTO);
    }

    private void setPicToView(Intent picdata) {
        Uri uri = picdata.getData();

        if (uri == null) {
            return;
        }
        String path = ImageUtils.getRealFilePathFromUri(getApplicationContext(), uri);

        uploadPic(path);
    }

    //上传头像
    private void uploadPic(String path) {
       /* if (path != null) {
            File file = new File(path);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("Filedata", file.getName(), requestFile);
            Map<String, RequestBody> map = new HashMap<>();
            RequestBody uidBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(AppConstant.UID));
         *//*   RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            map.put("Filedata", fileBody);*//*
            mPresenter.changeHeadSuccess(uidBody,body);
        }*/
    }
    @Override
    public void changeSuccess(String result) {
        ToastUtil.showShortToast(result);
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case NICKNAME_REQUEST:
                if(resultCode==NICKNAME_RESULT){
                    nicknameTv.setText(data.getExtras().getString("NICKNAME",""));
                    Map<String,String> map=new HashMap<>();
                    map.put("uid", String.valueOf(AppConstant.UID));
                    map.put("nickname", nicknameTv.getText().toString());
                    load(map);
                }
        }
    }


}
