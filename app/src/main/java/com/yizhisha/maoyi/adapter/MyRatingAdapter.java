package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;

import java.util.List;

/**
 * Created by lan on 2017/9/26.
 */

public class MyRatingAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MyRatingAdapter(@Nullable List<String> data) {
        super(R.layout.item_middle_tovualution,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.titel_tv,"甜美甜美甜美少女风系列连衣裙");
        helper.setText(R.id.price_tv,"168.00");
        helper.setText(R.id.date_tv,"2017-06-20");
        helper.setText(R.id.color_tv,"#12344");
        TextView commentTv=helper.getView(R.id.add_comment_tv);
        if(item.equals("评价")){
            commentTv.setText("立即评价");
        }else{
            commentTv.setText("追加评价");
        }
    }
}
