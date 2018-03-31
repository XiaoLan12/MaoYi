package com.yizhisha.maoyi.ui.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.bean.json.SortedBean;
import com.yizhisha.maoyi.ui.type.avtivity.TypeShopListActivity;
import com.yizhisha.maoyi.utils.ListDataSave;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @Bind(R.id.tagflowlayout)
    TagFlowLayout tagFlowLayout;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.tv_search)
    TextView tv_search;

    private TagAdapter<String> maAdapter;

    private List<String> dataList = new ArrayList<String>();
    private ListDataSave dataSave;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        final LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataSave=new ListDataSave(this,"history");
        dataList=dataSave.getDataList("historyTag");
        maAdapter=new TagAdapter<String>(dataList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
       tagFlowLayout.setAdapter(maAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("KEY", dataList.get(position));
                startActivity(TypeShopListActivity.class, bundle1);
                return false;
            }
        });
    }
    @OnClick({R.id.img_back,R.id.clean_history_tv,R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_back:
                finish_Activity(SearchActivity.this);
                break;
            case R.id.clean_history_tv:
                dataSave.clearData();
                dataList.clear();
                maAdapter.notifyDataChanged();
                break;

            case R.id.tv_search:

                String key=searchEt.getText().toString().trim();
                String value=key.replaceAll(" +"," ");
                String[] newKey=key.split("\\s+");
                int length=dataList.size();
                boolean isAdd=false;
                for(String str:newKey){
                    for(int i=0;i<length;i++){

                        if(dataList.get(i).equals(str)){
                            isAdd=true;
                        }
                    }
                    if(!isAdd) {
                        dataList.add(str);
                    }
                }
                dataSave.setDataList("historyTag",dataList);
                Bundle bundle1 = new Bundle();
                bundle1.putString("KEY", value);
                startActivity(TypeShopListActivity.class, bundle1);
                maAdapter.notifyDataChanged();
                break;

        }
    }
}
