package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.TuiguangItem1Bean;
import com.yizhisha.maoyi.utils.DateUtil;

import java.util.List;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem1Adapter  extends BaseQuickAdapter<TuiguangItem1Bean.Info,BaseViewHolder> {
    public TuiguangItem1Adapter(@Nullable List<TuiguangItem1Bean.Info> data) {
        super(R.layout.tuiguang_item1,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, TuiguangItem1Bean.Info item) {
        if(item.getRecord_tid()==1){
            helper.setText(R.id.type,"分销收入");
        }else if(item.getRecord_tid()==2){
            helper.setText(R.id.type,"提现收入");
        }
        helper.setText(R.id.order_num_tv,item.getRecord_id());
        helper.setText(R.id.join_time_tv, DateUtil.getDateToString1(item.getRecord_addtime()*1000));
        if(item.getRecord_type()==1){
            helper.setText(R.id.tv,"+"+item.getRecord_money());
        }else if(item.getRecord_type()==2){
            helper.setText(R.id.tv,"-"+item.getRecord_money());
        }

    }


}
