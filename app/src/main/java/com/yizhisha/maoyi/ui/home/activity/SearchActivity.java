package com.yizhisha.maoyi.ui.home.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.SortedBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class SearchActivity extends BaseActivity {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tv_clear)
    TextView tvClear;
    @Bind(R.id.id_flowlayout1)
    TagFlowLayout idFlowlayout1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
         List<String> mTabVal=new ArrayList<>();
        mTabVal.add("cehsi");
        mTabVal.add("cehsi");
        mTabVal.add("测试");


        idFlowlayout1.setAdapter(new TagAdapter<String>(mTabVal)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        idFlowlayout1, false);
                tv.setText(s);
                return tv;
            }
        });
    }
    @OnClick({R.id.img_back})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_back:
                finish_Activity(SearchActivity.this);
                break;
        }
    }
}
