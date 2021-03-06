package com.yizhisha.maoyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.GoodsBean;
import com.yizhisha.maoyi.bean.json.StoreBean;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.utils.GlideUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/6/28.
 */

public class ShoppCartAdapter extends BaseExpandableListAdapter {
    List<Map<String, Object>> parentMapList;
    List<List<Map<String, Object>>> childMapList_list;
    Context context;
    int totalCount = 0;
    double totalPrice = 0.00;
    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;
    OnGoodsCheckedChangeListener onGoodsCheckedChangeListener;
    OnCheckHasGoodsListener onCheckHasGoodsListener;
    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
    }
    public void setOnDeleteShopListener(OnDeleteShopListener monDeleteShopListener) {
        this.onDeleteShopListener = monDeleteShopListener;
    }
    public void setOnGoodsCheckedChangeListener(OnGoodsCheckedChangeListener onGoodsCheckedChangeListener) {
        this.onGoodsCheckedChangeListener = onGoodsCheckedChangeListener;
    }

    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }
    public void setOnGoodsEditChangeListenr(OnGoodsEditChangeListenr onGoodsEditChangeListenr) {
        this.onGoodsEditChangeListenr = onGoodsEditChangeListenr;
    }
    public ShoppCartAdapter(Context context, List<Map<String, Object>> parentMapList, List<List<Map<String, Object>>> childMapList_list) {
        this.parentMapList = parentMapList;
        this.childMapList_list = childMapList_list;
        this.context = context;
    }
    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        return parentMapList.size();
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapList_list.get(groupPosition).size();
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parentMapList.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapList_list.get(groupPosition).get(childPosition);
    }

    //得到父item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        //return false;
        return true;
    }
    //设置父item组件
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppcart1, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title_parent = (TextView) convertView
                    .findViewById(R.id.company_shoppcart1_tv);
            groupViewHolder.id_cb_select_parent = (CheckBox) convertView
                    .findViewById(R.id.groupshopp_cb);
            groupViewHolder.topDivider = (View) convertView
                    .findViewById(R.id.shoppcart1_view);

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");

        final String parentName = storeBean.getCompany();
        groupViewHolder.tv_title_parent.setText(parentName);

        groupViewHolder.id_cb_select_parent.setChecked(storeBean.isChecked());
        final boolean nowBeanChecked = storeBean.isChecked();
        groupViewHolder.id_cb_select_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
            }
        });
        if (groupPosition == 0) {
            groupViewHolder.topDivider.setVisibility(View.GONE);
        } else {
            groupViewHolder.topDivider.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    //设置子item的组件
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppcart2, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.id_cb_select_child = (CheckBox) convertView
                    .findViewById(R.id.childshopp_cb);
            childViewHolder.mIvPic= (ImageView) convertView.findViewById(R.id.tradehead_shoppcar2_iv);
            childViewHolder.mEditIv= (ImageView) convertView.findViewById(R.id.edit_iv);
            //常规下：
            childViewHolder.tv_items_child_desc = (TextView) convertView
                    .findViewById(R.id.tradename_shoppcar2_tv);
            childViewHolder.id_tv_price = (TextView) convertView
                    .findViewById(R.id.tradeprice_tv);
            childViewHolder.id_tv_color = (TextView) convertView
                    .findViewById(R.id.tradecolor_shoppcar2_tv);
            childViewHolder.id_tv_size = (TextView) convertView
                    .findViewById(R.id.tradesize_shoppcar2_tv);
            childViewHolder.id_tv_amount = (TextView) convertView
                    .findViewById(R.id.tradeamount_tv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final GoodsBean goodsBean = (GoodsBean) childMapList_list.get(groupPosition).get(childPosition).get("childName");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ProductDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("gid",goodsBean.getGid());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        //childViewHolder.id_tv_price.setText(String.format(context.getString(R.string.price), goodsBean.getPrice()+""));
        childViewHolder.id_cb_select_child.setChecked(goodsBean.isChecked());
        childViewHolder.tv_items_child_desc.setText(goodsBean.getTitle());
        childViewHolder.id_tv_price.setText(goodsBean.getPrice()+"");
        String detail=goodsBean.getDetail();
        if(detail!=null&&!detail.equals("")){
            childViewHolder.id_tv_color.setText("颜色:"+detail.substring(0,detail.indexOf("#")));
            childViewHolder.id_tv_size.setText("尺码:"+detail.substring(detail.indexOf("#")+1, detail.lastIndexOf("#")));
        }

        childViewHolder.id_tv_amount.setText("x"+goodsBean.getAmount());
        GlideUtil.getInstance().LoadContextBitmap(context, AppConstant.PRUDUCT_IMG_URL+goodsBean.getLitpic(),
                (ImageView) childViewHolder.mIvPic,GlideUtil.LOAD_BITMAP);
        childViewHolder.id_cb_select_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean nowBeanChecked = goodsBean.isChecked();
                //更新数据
                goodsBean.setChecked(!nowBeanChecked);
                boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(groupPosition);
                StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
                storeBean.setChecked(isOneParentAllChildIsChecked);
                notifyDataSetChanged();
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                dealPrice();
            }
        });
        childViewHolder.mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        childViewHolder.mEditIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGoodsEditChangeListenr.onEditChange(goodsBean);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }
    //供全选按钮调用
    public void setupAllChecked(boolean isChecked) {

        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            storeBean.setChecked(isChecked);
            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                goodsBean.setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
        dealPrice();
    }

    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) {
        StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
        storeBean.setChecked(isChecked);

        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            goodsBean.setChecked(isChecked);
        }
        notifyDataSetChanged();
        dealPrice();
    }

    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        // StoreBean storeBean= (StoreBean) parentMapList.get(groupPosition).get("parentName");
        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            if (!goodsBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public boolean dealAllParentIsChecked() {
        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            if (!storeBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public void dealPrice() {
        // showList();
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < parentMapList.size(); i++) {
            //StoreBean storeBean= (StoreBean) parentMapList.get(i).get("parentName");

            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                int count = goodsBean.getAmount();
                double price = goodsBean.getPrice();
                if (goodsBean.isChecked()) {
                    totalCount++;//单品多数量只记1
                    totalPrice += price * count;
                }

            }
        }
        //计算回调
        onGoodsCheckedChangeListener.onGoodsCheckedChange(totalCount, totalPrice);
    }
    public void removeGoods() {
        for (int i = parentMapList.size()-1; i>=0; i--) {//倒过来遍历  remove
            StoreBean storeBean= (StoreBean) parentMapList.get(i).get("parentName");
            if (storeBean.isChecked()){
                parentMapList.remove(i);
                childMapList_list.remove(i);
            }else {
                List<Map<String, Object>> childMapList = childMapList_list.get(i);
                for (int j = childMapList.size()-1; j >=0; j--) {//倒过来遍历  remove
                    GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                    if (goodsBean.isChecked()) {
                        childMapList.remove(j);
                    }
                }
            }

        }

        if (parentMapList != null && parentMapList.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();//
        dealPrice();//重新计算
    }
    public void removeOneGood(int groupPosition, int childPosition) {
        //StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
        List<Map<String, Object>> childMapList = childMapList_list.get(childPosition);
        // GoodsBean goodsBean = (GoodsBean) childMapList.get(childPosition).get("childName");
        childMapList.remove(childPosition);

        //通过子项
        if (childMapList!=null&&childMapList.size()>0){

        }else {
            parentMapList.remove(groupPosition);
            childMapList_list.remove(groupPosition);//！！！！因为parentMapList和childMapList_list是pos关联的  得保持一致
        }
        if (parentMapList != null && parentMapList.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();
        dealPrice();
    }


    //供总编辑按钮调用
    public void setupEditingAll(int isEditingAll) {
        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            storeBean.setEditing(isEditingAll);

            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                goodsBean.setEditing(isEditingAll);
            }
        }
        notifyDataSetChanged();
    }
    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }

    public interface OnEditingTvChangeListener {
        void onEditingTvChange(boolean allIsEditing);
    }
    OnEditingTvChangeListener onEditingTvChangeListener;

    public interface OnDeleteShopListener {
        void onDeleteShop(int groupPosition, int childPosition);
    }
    OnDeleteShopListener onDeleteShopListener;

    public interface OnGoodsCheckedChangeListener {
        void onGoodsCheckedChange(int totalCount, double totalPrice);
    }

    public interface OnCheckHasGoodsListener {
        void onCheckHasGoods(boolean isHasGoods);
    }
    OnGoodsEditChangeListenr onGoodsEditChangeListenr;
    public interface OnGoodsEditChangeListenr{
        void onEditChange(GoodsBean goodsBean);
    }
    class GroupViewHolder {
        TextView tv_title_parent;
        CheckBox id_cb_select_parent;
        View topDivider;
    }

    class ChildViewHolder {
        CheckBox id_cb_select_child;
        ImageView mIvPic;
        ImageView mEditIv;

        TextView tv_items_child_desc;
        TextView id_tv_price;
        TextView id_tv_color;
        TextView id_tv_size;
        TextView id_tv_amount;
    }
    public void setNewData(){
        notifyDataSetChanged();
    }
}
