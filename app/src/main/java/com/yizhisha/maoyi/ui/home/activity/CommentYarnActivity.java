package com.yizhisha.maoyi.ui.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.CommentYarnAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.json.MyCommentBean;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.ui.home.contract.CommentYarnContract;
import com.yizhisha.maoyi.ui.home.presenter.CommentYarnPresenter;
import com.yizhisha.maoyi.widget.CommonLoadingView;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class CommentYarnActivity extends BaseActivity<CommentYarnPresenter> implements
        CommentYarnContract.View  {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.comment_rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private List<MyCommentListBean> dataList=new ArrayList<>();
    private CommentYarnAdapter mAdapter;
    private int id;
    private int type;
    private final String COMMENTURL="http://www.taoshamall.com/data/attached/comment/";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_yarn;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(CommentYarnActivity.this);
            }
        });
    }
    //ceshitijiao

    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=bundle.getInt("ID");
        }
        initAdapter();
        load(id,true);
    }
    private void initAdapter(){
        mAdapter=new CommentYarnAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(this, LinearLayoutManager.VERTICAL));

    }
    private void load(int id,boolean isShowLoad) {
        Map<String, String> bodyMap = new HashMap<>();
        //暂时写死，发布时替换

        mPresenter.loadCommentList(id, isShowLoad);
    }

    @Override
    public void loadCommentListSuccess(List<MyCommentListBean> data) {
        for (int i = 0; i < data.size(); i++) {
            MyCommentListBean.MyComment comment=data.get(i).getComment();
            if (comment.getComment_photos()!= null&&!"".equals(comment.getComment_photos())) {
                String date[]=comment.getComment_photos().split(",");
                List<String> list=new ArrayList<>();
                for (int j = 0; j < date.length; j++) {
                    list.add(COMMENTURL+date[j]);
                }
                comment.setCommentPhotos(list);
                data.get(i).setItemType(MyCommentListBean.IMGS_TYPE);
            }else {

                data.get(i).setItemType(MyCommentListBean.TEXT_TYPE);
            }
        }
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void showLoading() {
        mLoadingView.load();
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }

    @Override
    public void showEmpty() {
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(id,true);
            }
        });
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }
    @OnClick({R.id.allcoment_tv,R.id.goodcoment_tv,R.id.mediumcomment_tv,
            R.id.badcomment_tv, R.id.havepiccomment_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.allcoment_tv:
                type=0;
                load(id,false);
                break;
            case R.id.goodcoment_tv:
                type=1;
                load(id,false);
                break;
            case R.id.mediumcomment_tv:
                type=2;
                load(id,false);
                break;
            case R.id.badcomment_tv:
                type=3;
                load(id,false);
                break;
            case R.id.havepiccomment_tv:
                type=4;
                load(id,false);
                break;
        }
    }
}
