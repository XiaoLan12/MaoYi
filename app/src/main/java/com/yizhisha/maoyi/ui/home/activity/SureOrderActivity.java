package com.yizhisha.maoyi.ui.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.OrderSureAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.bean.RadioBean;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.common.dialog.RadioSelectionDialog;
import com.yizhisha.maoyi.ui.home.contract.SureOrderContract;
import com.yizhisha.maoyi.ui.home.presenter.SureOrderPresenter;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class SureOrderActivity extends BaseActivity<SureOrderPresenter> implements SureOrderContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consignee_name_tv)
    TextView consigneeNameTv;
    @Bind(R.id.consignee_phone_tv)
    TextView consigneePhoneTv;
    @Bind(R.id.shippingaddress_tv)
    TextView shippingaddressTv;

    @Bind(R.id.distribution_way_tv)
    TextView distributionWayTv;
    @Bind(R.id.zhifubao_cb)
    CheckBox zhifubaoCb;
    @Bind(R.id.weixin_cb)
    CheckBox weixinCb;

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private OrderSureAdapter mAdapter;
    private List<OrderSureBean.Goods> dataList=new ArrayList<>();

    private RadioSelectionDialog radioSelectionDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_order;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initAdapter();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            loadOrder(bundle);
        }
    }
    //初始化一般商品适配器
    private void initAdapter() {
        mAdapter = new OrderSureAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //startActivity(YarnActivity.class);
            }
        });
    }
    private void loadOrder(Bundle bundle){
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",String.valueOf(bundle.getInt("gid",0)));
        map.put("detail",bundle.getString("detail"));
        map.put("amount",String.valueOf(bundle.getInt("amount")));
        mPresenter.loadOrderSure(map);
    }
    @Override
    public void loadOrderSuccess(OrderSureBean data) {
        dataList.clear();
        dataList.add(data.getGoods());
        mAdapter.setNewData(dataList);
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            List<OrderSureBean.Address> addressList=data.getAddress();
            for(OrderSureBean.Address address:addressList){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }

        }
    }
    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
    @OnClick({R.id.zhifubao_rl,R.id.weixin_rl,R.id.distribution_way_rl})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhifubao_rl:
                zhifubaoCb.setChecked(true);
                weixinCb.setChecked(false);

                break;
            case R.id.weixin_rl:
                zhifubaoCb.setChecked(false);
                weixinCb.setChecked(true);
                break;
            case R.id.distribution_way_rl:
                if(radioSelectionDialog==null) {
                    List<RadioBean> list = new ArrayList<>();
                    RadioBean radioBean = new RadioBean();
                    radioBean.setItem("快递 免邮");
                    radioBean.setIndex(0);
                    radioBean.setSelected(true);

                    RadioBean radioBean1 = new RadioBean();
                    radioBean1.setItem("物流");
                    radioBean1.setIndex(1);
                    radioBean1.setSelected(false);

                    list.add(radioBean);
                    list.add(radioBean1);
                    radioSelectionDialog = new RadioSelectionDialog(this, "配送方式");
                    radioSelectionDialog.setItem(list);

                    radioSelectionDialog.setOnSelectItemListener(new RadioSelectionDialog.OnSelectItemListener() {
                        @Override
                        public void onSelectItem(RadioBean bean) {
                            distributionWayTv.setText(bean.getItem());
                        }
                    });
                }
                    radioSelectionDialog.show();
                break;
        }
    }
}
