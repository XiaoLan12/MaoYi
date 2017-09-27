package com.yizhisha.maoyi.ui.me.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.ChoosePhotoListAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.widget.CommonLoadingView;

import java.util.ArrayList;

import butterknife.Bind;

public class ApplyRefundActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    private ChoosePhotoListAdapter mAdapter;
    private ArrayList<String> path = new ArrayList<String>();
    private ArrayList<String> loadPath = new ArrayList<String>();

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        mLoadingView.loadSuccess();
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
    private void showPhotoPicker(){
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
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);
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
