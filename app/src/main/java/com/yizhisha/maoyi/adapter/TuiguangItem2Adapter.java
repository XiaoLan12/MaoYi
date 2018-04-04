package com.yizhisha.maoyi.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.TuiguangItem2Bean;
import com.yizhisha.maoyi.utils.DateUtil;

import java.util.List;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem2Adapter  extends BaseQuickAdapter<TuiguangItem2Bean.Info,BaseViewHolder> {
    public TuiguangItem2Adapter(@Nullable List<TuiguangItem2Bean.Info> data) {
        super(R.layout.tuiguang_item2,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuiguangItem2Bean.Info item) {
        if(!item.getNickname().equals("")){
            helper.setText(R.id.username_tv,item.getNickname());
        }else if(!item.getMobile().equals("")){
            String phone=item.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
            helper.setText(R.id.username_tv,"手机号:"+phone);
        }
        helper.setText(R.id.join_time_tv, DateUtil.getDateToString1(item.getRegtime()*1000));
        helper.setText(R.id.tv,item.getSex()==1?"男":"女");

    }
}
