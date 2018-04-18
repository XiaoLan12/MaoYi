package com.yizhisha.maoyi.ui.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.OrderSureAdapter;
import com.yizhisha.maoyi.base.BaseActivity;
import com.yizhisha.maoyi.base.BaseToolbar;
import com.yizhisha.maoyi.base.rx.RxBus;
import com.yizhisha.maoyi.bean.RadioBean;
import com.yizhisha.maoyi.bean.json.GoodsListBean;
import com.yizhisha.maoyi.bean.json.OrderSureBean;
import com.yizhisha.maoyi.bean.json.RequestStatusBean;
import com.yizhisha.maoyi.bean.json.ShopCartOrderSureBean;
import com.yizhisha.maoyi.common.dialog.DialogInterface;
import com.yizhisha.maoyi.common.dialog.LoadingDialog;
import com.yizhisha.maoyi.common.dialog.NormalAlertDialog;
import com.yizhisha.maoyi.common.dialog.RadioSelectionDialog;
import com.yizhisha.maoyi.event.FinishEvent;
import com.yizhisha.maoyi.event.UpdateShopCartEvent;
import com.yizhisha.maoyi.event.WeChatPayEvent;
import com.yizhisha.maoyi.ui.home.contract.SureOrderContract;
import com.yizhisha.maoyi.ui.home.presenter.SureOrderPresenter;
import com.yizhisha.maoyi.ui.me.activity.MyAddressActivity;
import com.yizhisha.maoyi.ui.me.activity.MyOrderActivity;
import com.yizhisha.maoyi.utils.ToastUtil;
import com.yizhisha.maoyi.widget.RecyclerViewDriverLine;
import com.yizhisha.maoyi.wxapi.WeChatPayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SureOrderActivity extends BaseActivity<SureOrderPresenter> implements SureOrderContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consignee_name_tv)
    TextView consigneeNameTv;
    @Bind(R.id.consignee_phone_tv)
    TextView consigneePhoneTv;
    @Bind(R.id.shippingaddress_tv)
    TextView shippingaddressTv;
    @Bind(R.id.cost_tv)
    TextView costTv;
    @Bind(R.id.remark_et)
    EditText mRemarkEt;
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
    private OrderSureBean.Goods goods;
    private RadioSelectionDialog radioSelectionDialog;
    private int mType=0;//当前订单类型,0:普通订单  1:购物车订单
    private LoadingDialog mLoadingDialog;
    private Subscription subscription;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_order;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish_Activity(SureOrderActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        initAdapter();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mType=bundle.getInt("ORDERTYPE");
            if(mType==0){
                loadOrder(bundle);
            }else if(mType==1){
                loadShoppOrder(bundle);
            }
        }
        event();
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
                startActivity(ProductDetailActivity.class);
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
    private void loadShoppOrder(Bundle bundle){
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("sid",bundle.getString("sid",""));
        mPresenter.loadShopCartOrderSure(map);
    }

    //生成普通订单
    private void createOrder(){
        StringBuilder str=new StringBuilder();
        StringBuilder gidStr=new StringBuilder();
        String detail="";
        String gid="";
        for(int i=0;i<dataList.size();i++){
            str.append(dataList.get(i).getDetail()).append("#").append(dataList.get(i).getAmount()).append(",");
            gidStr.append(dataList.get(i).getGid()).append(",");

        }
        if(str.length()>0){
            detail=str.substring(0,str.length()-1);
        }
        if(gidStr.length()>0){
            gid=gidStr.substring(0,gidStr.length()-1);
        }
        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("gid",gid);
        body.put("totalprice",String.valueOf(goods.getTotalprice()));
        body.put("realprice",String.valueOf(goods.getRealprice()));
        body.put("addressid",String.valueOf(goods.getAddressId()));
        body.put("payment",String.valueOf(2));
        body.put("detail",detail);
        body.put("remark",mRemarkEt.getText().toString());
        mPresenter.createOrder(body);
    }
    //创建购物车订单
    private void createShopCartOrder(){
        StringBuilder gidStr=new StringBuilder();
        String sid="";
        for(int i=0;i<dataList.size();i++){
            gidStr.append(dataList.get(i).getSid()).append(",");

        }
        if(gidStr.length()>0){
            sid=gidStr.substring(0,gidStr.length()-1);
        }
        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("sid",sid);
        body.put("addressid",String.valueOf(goods.getAddressId()));
        body.put("realprice",String.valueOf(goods.getRealprice()));
        body.put("payment",String.valueOf(2));
        mPresenter.createShopCartOrder(body);
    }
    @Override
    public void loadOrderSuccess(OrderSureBean data) {
        dataList.clear();
        dataList.add(data.getGoods());
        mAdapter.setNewData(dataList);
        goods=null;
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
        goods=data.getGoods();
        goods.setAddressId(addressId);
        costTv.setText("合计:￥"+goods.getTotalprice());

    }
    @Override
    public void loadShopCartOrderSuccess(ShopCartOrderSureBean data) {
        dataList.clear();
        List<ShopCartOrderSureBean.Shopcart> list=new ArrayList<>();
        list.addAll(data.getShopcart());
        int lenght=list.size();
        for (int i=0;i<lenght;i++){
            OrderSureBean.Goods goods=new OrderSureBean().new Goods();
            goods.setGid(list.get(i).getGid());
            goods.setAmount(list.get(i).getAmount());
            goods.setDetail(list.get(i).getDetail());
            goods.setLitpic(list.get(i).getLitpic());
            goods.setPrice(list.get(i).getPrice());
            goods.setTitle(list.get(i).getTitle());
            goods.setRealprice(data.getRealprice());
            goods.setSid(list.get(i).getId());
            dataList.add(goods);
        }
        mAdapter.setNewData(dataList);
        goods=null;
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            List<ShopCartOrderSureBean.Address> addressList=data.getAddress();
            for(ShopCartOrderSureBean.Address address:addressList){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }
        }

        goods=dataList.get(0);
        goods.setAddressId(addressId);
        costTv.setText("合计:￥"+goods.getTotalprice());
    }

    //回调事件，成功调起微信支付后响应该事件
    private void event(){
        subscription= RxBus.$().toObservable(WeChatPayEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatPayEvent>() {
                    @Override
                    public void call(WeChatPayEvent event) {
                        new NormalAlertDialog.Builder(SureOrderActivity.this)
                                .setTitleText("支付结果")
                                .setContentText("支付成功")
                                .setSingleModel(true)
                                .setSingleText("确认")
                                .setWidth(0.75f)
                                .setHeight(0.33f)
                                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                                    @Override
                                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                        startActivity(MyOrderActivity.class);
                                        RxBus.$().postEvent(new FinishEvent());
                                        finish_Activity(SureOrderActivity.this);
                                    }
                                }).build().show();
                    }
                });
    }
    @Override
    public void createOrderSuccess(RequestStatusBean bean) {
        WeChatPayService weChatPay = new WeChatPayService(this, 0, bean.getOrderno(), dataList.get(0).getTitle(), goods.getTotalprice()+"");
        weChatPay.pay();
    }

    @Override
    public void createShopCartOrderSuccess(RequestStatusBean bean) {
        RxBus.$().postEvent(new UpdateShopCartEvent());
        WeChatPayService weChatPay = new WeChatPayService(this, 0, "1", dataList.get(0).getTitle(), goods.getPrice()+"");
        weChatPay.pay();
    }

    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(this,"请稍后...",false);
        mLoadingDialog.show();
    }
    @Override
    public void hideLoading() {
        mLoadingDialog.cancelDialog();
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
    @OnClick({R.id.zhifubao_rl,R.id.weixin_rl,R.id.distribution_way_rl ,R.id.shippingaddress_ll,
            R.id.sureorder_tv
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhifubao_rl:
                zhifubaoCb.setChecked(true);
                weixinCb.setChecked(false);

                break;
            case R.id.sureorder_tv:
                if(!weixinCb.isChecked()){
                    ToastUtil.showShortToast("请选择支付方式");
                    return;
                }
                if(mType==0){
                    createOrder();
                }else{
                    createShopCartOrder();
                }

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
            case R.id.shippingaddress_ll:
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",1);
                startActivityForResult(MyAddressActivity.class,bundle,2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==2){
            GoodsListBean.Address address= (GoodsListBean.Address) data.getSerializableExtra("ADDRESS");
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            goods.setAddressId(address.getId());
            shippingaddressTv.setText(address.getAddress());
        }
    }
}
