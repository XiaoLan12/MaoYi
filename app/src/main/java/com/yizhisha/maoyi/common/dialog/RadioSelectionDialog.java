package com.yizhisha.maoyi.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.RadioBean;
import com.yizhisha.maoyi.utils.DensityUtil;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class RadioSelectionDialog {
    /** 加载数据对话框 */
    private Dialog mRadioDialog;
    private String msg;
    private Context mcontext;
    private List<RadioBean> dataLists;
    private RecyclerView recyclerView;
    private RadioAdapter mAdapter;
    /**
     * 显示加载对话框
     * @param context 上下文
     */
    public RadioSelectionDialog(Context context){
        this(context,"");
    }
    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     */
    public RadioSelectionDialog(Context context, String msg){
        this.mcontext=context;
        this.msg=msg;
        init();
    }
    private void init(){
        View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_radio, null);
        TextView titleText = (TextView)view.findViewById(R.id.title_tv);
        titleText.setText(msg);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        mRadioDialog = new Dialog(mcontext, R.style.DialogStyle);
        mRadioDialog.setCanceledOnTouchOutside(true);
        mRadioDialog.setContentView(view, new LinearLayout.LayoutParams(DensityUtil.getScreenWidth(mcontext), LinearLayout.LayoutParams.MATCH_PARENT));
    }
    public void setItem(List<RadioBean> list){
        dataLists=new ArrayList<>();
        dataLists.addAll(list);
        mAdapter=new RadioAdapter(dataLists);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        recyclerView.addItemDecoration(new RecyclerViewDriverLine(mcontext, LinearLayoutManager.VERTICAL));
    }
    public void show(){
        if(mRadioDialog != null) {
            mRadioDialog.show();
        }
    }
    /**
     * 关闭加载对话框
     */
    public  void cancelDialog() {
        if(mRadioDialog != null) {
            mRadioDialog.cancel();
        }
    }
    public interface OnSelectItemListener {
        void onSelectItem(RadioBean bean);
    }
    private OnSelectItemListener onSelectItemListener;
    public void setOnSelectItemListener(OnSelectItemListener onSelectItemListener){
        this.onSelectItemListener=onSelectItemListener;
    }

    class  RadioAdapter extends BaseQuickAdapter<RadioBean,BaseViewHolder>{

        public RadioAdapter(@Nullable List<RadioBean> data) {
            super(R.layout.item_radio,data);
        }
        @Override
        protected void convert(final BaseViewHolder helper, RadioBean item) {
            helper.setText(R.id.name_tv,item.getItem());
            final CheckBox checkBox=helper.getView(R.id.cb);
            if(item.isSelected()){
                checkBox.setBackgroundResource(R.drawable.ic_checkbox_seleted1);
            }else{
                checkBox.setBackgroundResource(R.drawable.ic_checkbox_normal);
            }
            helper.setOnClickListener(R.id.rl, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<dataLists.size();i++){
                        dataLists.get(i).setSelected(false);
                    }
                    dataLists.get(helper.getAdapterPosition()).setSelected(true);
                    onSelectItemListener.onSelectItem(dataLists.get(helper.getAdapterPosition()));
                    notifyDataSetChanged();

                    cancelDialog();
                }
            });
        }
    }
}
