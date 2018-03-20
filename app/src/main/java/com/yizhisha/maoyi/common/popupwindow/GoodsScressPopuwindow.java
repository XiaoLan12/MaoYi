package com.yizhisha.maoyi.common.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.GoodsScreesBean;
import com.yizhisha.maoyi.bean.json.GoodsScreesContentBean;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.OrderHeadBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 小熊 on 2018/2/9.
 */

public class GoodsScressPopuwindow extends PopupWindow{
    private View mContentView;
    private Context mActivity;
    private RecyclerView recyclerView;
    private List<Object> goodsScreesBeanList;
    private GoodsScressAdapter mAdapter;
    private TagFlowLayout flowlayout1,flowlayout2,flowlayout3;
    private TextView tv_search;
    private  LayoutInflater mInflater;
    private SearchClickListener searchClickListener;
    private EditText et_price1,et_price2;

    private List<String > list=new ArrayList<>();

    public GoodsScressPopuwindow(Context activity){
        mActivity=activity;
        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) activity
                .getSystemService(Context.WINDOW_SERVICE);
        int mScreenWidth = wm.getDefaultDisplay().getWidth();
        setWidth((int) (mScreenWidth*0.6));
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setAnimationStyle(R.style.showPopupAnimation);
        goodsScreesBeanList=new ArrayList<>();
        mContentView = LayoutInflater.from(activity).inflate(R.layout.popu_goods_scress, null);
        setContentView(mContentView);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(false);
        setTouchable(true);

          mInflater = LayoutInflater.from(activity);
       flowlayout1= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout1);
        flowlayout2= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout2);
        flowlayout3= (TagFlowLayout ) mContentView.findViewById(R.id.id_flowlayout3);
        recyclerView= (RecyclerView) mContentView.findViewById(R.id.recyclerview);
        et_price1=(EditText) mContentView.findViewById(R.id.et_price1);
        et_price2=(EditText) mContentView.findViewById(R.id.et_price2);
        tv_search=(TextView)mContentView.findViewById(R.id.tv_search);
        mAdapter=new GoodsScressAdapter();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);
       /* flowlayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                Log.e("UUU",selectPosSet.toString());
                for(Integer c : selectPosSet) {
                    Log.e("UUU",c+"");
                }
            }
        });*/

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchClickListener!=null){
                    List<Integer > list=new ArrayList<>();
                   String price1= et_price1.getText().toString().trim();
                    String price2= et_price2.getText().toString().trim();
                    if(!price1.equals("")){
                        try {
                            list.add(Integer.parseInt(price1));
                        }catch (Exception e){

                        }
                    }
                    if(!price2.equals("")){
                        try {
                            list.add(Integer.parseInt(price2));
                        }catch (Exception e){

                        }
                    }
                    if(list.size()==2){
                        if(list.get(0)>list.get(1)){
                            et_price1.setText(list.get(1)+"");
                            et_price2.setText(list.get(0)+"");
                        }
                    }
                    searchClickListener.searchClickLIstener(  flowlayout1.getSelectedList(),flowlayout2.getSelectedList(),flowlayout3.getSelectedList(),list);
                }

                dismiss();
            }
        });


    }
    public void serData1(String[] mVals1,String[] mVals2,String[] mVals3) {
        flowlayout1.setAdapter(new TagAdapter<String>(mVals1)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout1, false);
                tv.setText(s);
                return tv;
            }
        });

        flowlayout2.setAdapter(new TagAdapter<String>(mVals2)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout2, false);
                tv.setText(s);
                return tv;
            }
        });
        flowlayout3.setAdapter(new TagAdapter<String>(mVals3)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        flowlayout3, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    public void serData(List<Object> data){
        goodsScreesBeanList.clear();

        goodsScreesBeanList.addAll(data);
        mAdapter.setNewData(goodsScreesBeanList);
    }

    private class GoodsScressAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
        public static final int TEXT_TYPE1 = 1;
        public static final int TEXT_TYPE2= 2;
        public GoodsScressAdapter() {
            super(null);
            setMultiTypeDelegate(new MultiTypeDelegate<Object>() {
                @Override
                protected int getItemType(Object object) {
                    if(object instanceof GoodsScreesBean) {
                        return TEXT_TYPE1;
                    }else if(object instanceof GoodsScreesContentBean){
                        return TEXT_TYPE2;
                    }
                    return TEXT_TYPE2;
                }
            });
            getMultiTypeDelegate().registerItemType(TEXT_TYPE1, R.layout.item_goods_screes_head).
                    registerItemType(TEXT_TYPE2, R.layout.item_goods_screes_content);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            switch (helper.getItemViewType()){
                case TEXT_TYPE1:
                    GoodsScreesBean goodsScreesBean= (GoodsScreesBean) item;
                    helper.setText(R.id.head_tv,goodsScreesBean.getItem());
                    break;
                case TEXT_TYPE2:
                    GoodsScreesContentBean goodsScreesContentBean= (GoodsScreesContentBean) item;
                    helper.setText(R.id.content_tv,goodsScreesContentBean.getTitle());
                    break;
            }
        }
    }
    public interface SearchClickListener{
        public void searchClickLIstener(Set<Integer> selectPosSet1,Set<Integer> selectPosSet2,Set<Integer> selectPosSet3,List<Integer> list);
    }
    public void setSearchClickListener(SearchClickListener searchClickListener){
        this.searchClickListener=searchClickListener;
    }


}
