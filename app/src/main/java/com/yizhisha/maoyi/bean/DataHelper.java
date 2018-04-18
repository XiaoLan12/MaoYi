package com.yizhisha.maoyi.bean;
import com.yizhisha.maoyi.AppConstant;
import com.yizhisha.maoyi.bean.json.MyCommentHeadBean;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.bean.json.MyOrderListBean;
import com.yizhisha.maoyi.bean.json.OrderFootBean;
import com.yizhisha.maoyi.bean.json.OrderHeadBean;
import com.yizhisha.maoyi.bean.json.RefundFootBean;
import com.yizhisha.maoyi.bean.json.RefundHeadBean;
import com.yizhisha.maoyi.bean.json.RefundListBean;
import com.yizhisha.maoyi.bean.json.ToEvalutionFootBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/8.
 */

public class DataHelper {
    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static ArrayList<Object> getDataAfterHandle(List<MyOrderListBean> resultList) {

        ArrayList<Object> dataList = new ArrayList<Object>();

        //遍历每一张大订单
        for (MyOrderListBean order : resultList) {
            //大订单支付的金额核定单状态
            OrderHeadBean orderHeadBean = new OrderHeadBean();
            orderHeadBean.setStatus(order.getStatus());
            orderHeadBean.setOrderno(order.getOrderno());
            dataList.add(orderHeadBean);


            List<MyOrderListBean.Goods> goodses=order.getGoods();
            //遍历每个大订单里面的小订单
            for (MyOrderListBean.Goods orderGoodsItem : goodses) {
                orderGoodsItem.setOrderno(order.getOrderno());
                orderGoodsItem.setStatue(order.getStatus());
                dataList.add(orderGoodsItem);
            }
            MyOrderListBean.Refund refund=order.getRefund();

            OrderFootBean orderFootBean=new OrderFootBean();
            orderFootBean.setId(order.getId());
            orderFootBean.setTotalprice(order.getTotalprice());
            orderFootBean.setStatus(order.getStatus());
            orderFootBean.setAmount(goodses.size());
            orderFootBean.setOrderno(order.getOrderno());
            if(refund!=null){
                orderFootBean.setType(order.getRefund().getType());
            }
            dataList.add(orderFootBean);
        }
        return dataList;
    }
    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static ArrayList<Object> getDataEvalution(List<MyOrderListBean> resultList) {
        StringBuffer str=new StringBuffer();
        ArrayList<Object> dataList = new ArrayList<Object>();
        //遍历每一张大订单
        for (MyOrderListBean order : resultList) {
            str.setLength(0);
            List<MyOrderListBean.Goods> goodses=order.getGoods();
            //遍历每个大订单里面的小订单
            for (MyOrderListBean.Goods orderGoodsItem : goodses) {
                orderGoodsItem.setOrderno(order.getOrderno());
                str.append(orderGoodsItem.getDetail()).append(";");
                dataList.add(orderGoodsItem);
            }
            ToEvalutionFootBean footBean=new ToEvalutionFootBean();
            footBean.setDetail(str.toString());
            footBean.setAddtime(order.getAddtime());
            footBean.setOrderno(order.getOrderno());
            footBean.setOrderId(order.getId());
            footBean.setCommentstatus(order.getCommentstatus());
            footBean.setStatus(order.getStatus());
            dataList.add(footBean);
        }
        return dataList;
    }
    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static ArrayList<Object> getCommentDataAfterHandle(List<MyCommentListBean> resultList) {
        String orderno="";
        ArrayList<Object> dataList = new ArrayList<Object>();


        for (MyCommentListBean commentListBean : resultList) {
            MyCommentHeadBean headBean = new MyCommentHeadBean();
            MyCommentListBean.MyComment myComment = commentListBean.getComment();
            headBean.setComment_addtime(myComment.getComment_addtime());
            if(commentListBean.getGoods().size()>0){
                headBean.setOrderno(commentListBean.getGoods().get(0).getOrderno());
            }
            dataList.add(headBean);


            List<MyCommentListBean.Goods> goodses = commentListBean.getGoods();
            //遍历每个大订单里面的小订单
            for (MyCommentListBean.Goods orderGoodsItem : goodses) {
                dataList.add(orderGoodsItem);
                orderno=orderGoodsItem.getOrderno();
            }
            if (myComment.getComment_photos() != null && !"".equals(myComment.getComment_photos())) {
                String date[] = myComment.getComment_photos().split(",");
                List<String> list = new ArrayList<>();
                for (int j = 0; j < date.length; j++) {
                    list.add(AppConstant.COMENT_IMG_URL+date[j]);
                }
                myComment.setCommentPhotos(list);
            }
            dataList.add(myComment);
        }
        return dataList;
    }

    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static ArrayList<Object> getDataRefund(List<RefundListBean> resultList) {

        ArrayList<Object> dataList = new ArrayList<Object>();

        //遍历每一张大订单
        for (RefundListBean order : resultList) {
            RefundListBean.Refund refund=order.getRefund();
            //大订单支付的金额核定单状态
            RefundHeadBean orderHeadBean = new RefundHeadBean();
            orderHeadBean.setOrderno(refund.getOrderno());
            orderHeadBean.setRefundno(refund.getRefundno());
            dataList.add(orderHeadBean);
            List<RefundListBean.Goods> goodses=order.getGoods();
            //遍历每个大订单里面的小订单
            for (RefundListBean.Goods orderGoodsItem : goodses) {
                //orderGoodsItem.setOrderno(order.getOrderno());
                dataList.add(orderGoodsItem);
            }

            RefundFootBean orderFootBean=new RefundFootBean();
            orderFootBean.setOrderno(refund.getOrderno());
            orderFootBean.setRefundno(refund.getRefundno());
            orderFootBean.setRefundstatus(refund.getRefundstatus());
            orderFootBean.setType(refund.getType());
            dataList.add(orderFootBean);
        }
        return dataList;
    }
}
