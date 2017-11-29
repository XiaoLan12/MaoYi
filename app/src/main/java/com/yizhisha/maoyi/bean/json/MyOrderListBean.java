package com.yizhisha.maoyi.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class MyOrderListBean implements Serializable{
    private int id;
    private String orderno;
    private int status;
    private int amount;
    private double totalprice;
    private int addtime;
    private Address address;
    private String address_id;
    private String payorderno;
    private String express_no;
    private int express_id;
    private int refundstatus;
    private int paytime;
    private int shiptime;
    private int receivetime;
    private int donetime;
    private List<Goods> goods;
    //private Refund refund;

    public int getId() {
        return id;
    }

    public String getOrderno() {
        return orderno;
    }

    public int getStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public int getAddtime() {
        return addtime;
    }

    public Address getAddress() {
        return address;
    }

    public String getAddress_id() {
        return address_id;
    }

    public String getPayorderno() {
        return payorderno;
    }

    public String getExpress_no() {
        return express_no;
    }

    public int getExpress_id() {
        return express_id;
    }

    public int getRefundstatus() {
        return refundstatus;
    }

    public int getPaytime() {
        return paytime;
    }

    public int getShiptime() {
        return shiptime;
    }

    public int getReceivetime() {
        return receivetime;
    }

    public int getDonetime() {
        return donetime;
    }

    public List<Goods> getGoods() {
        return goods;
    }

   /* public Refund getRefund() {
        return refund;
    }*/

    public class Address {

        private String uid;
        private String linkman;
        private String mobile;
        private String area_pname;
        private String area_sname;
        private String address;
        private String area_app;
        public void setUid(String uid) {
            this.uid = uid;
        }
        public String getUid() {
            return uid;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
        public String getLinkman() {
            return linkman;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getMobile() {
            return mobile;
        }

        public void setAreaPname(String areaPname) {
            this.area_pname = areaPname;
        }
        public String getAreaPname() {
            return area_pname;
        }

        public void setAreaSname(String areaSname) {
            this.area_sname = areaSname;
        }
        public String getAreaSname() {
            return area_sname;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setAreaApp(String areaApp) {
            this.area_app = areaApp;
        }
        public String getAreaApp() {
            return area_app;
        }

    }
    public class Goods {

        private String gid;
        private String title;
        private String price;
        private String amount;
        private double totalprice;
        private String litpic;
        private String detail;
        private String remark;
        private String orderno;
        public void setGid(String gid) {
            this.gid = gid;
        }
        public String getGid() {
            return gid;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getPrice() {
            return price;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
        public String getAmount() {
            return amount;
        }

        public void setTotalprice(double totalprice) {
            this.totalprice = totalprice;
        }
        public double getTotalprice() {
            return totalprice;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
        public String getLitpic() {
            return litpic;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
        public String getDetail() {
            return detail;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public String getRemark() {
            return remark;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }
    }
    public class Refund {

        private String id;
        private String refundno;
        private String orderno;
        private String uid;
        private String type;
        private String reason;
        private String detail;
        private String pic;
        private String money;
        private String refundstatus;
        private String addtime;
        private String replytime;
        private String starttime;
        private String shiptime;
        private String receivetime;
        private String rejectreason;
        private String shipexpressid;
        private String shipexpressno;
        private String shipmobile;
        private String shipdetail;
        private String refundtime;
        private String user_del;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setRefundno(String refundno) {
            this.refundno = refundno;
        }
        public String getRefundno() {
            return refundno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }
        public String getOrderno() {
            return orderno;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
        public String getUid() {
            return uid;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
        public String getDetail() {
            return detail;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
        public String getPic() {
            return pic;
        }

        public void setMoney(String money) {
            this.money = money;
        }
        public String getMoney() {
            return money;
        }

        public void setRefundstatus(String refundstatus) {
            this.refundstatus = refundstatus;
        }
        public String getRefundstatus() {
            return refundstatus;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
        public String getAddtime() {
            return addtime;
        }

        public void setReplytime(String replytime) {
            this.replytime = replytime;
        }
        public String getReplytime() {
            return replytime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }
        public String getStarttime() {
            return starttime;
        }

        public void setShiptime(String shiptime) {
            this.shiptime = shiptime;
        }
        public String getShiptime() {
            return shiptime;
        }

        public void setReceivetime(String receivetime) {
            this.receivetime = receivetime;
        }
        public String getReceivetime() {
            return receivetime;
        }

        public void setRejectreason(String rejectreason) {
            this.rejectreason = rejectreason;
        }
        public String getRejectreason() {
            return rejectreason;
        }

        public void setShipexpressid(String shipexpressid) {
            this.shipexpressid = shipexpressid;
        }
        public String getShipexpressid() {
            return shipexpressid;
        }

        public void setShipexpressno(String shipexpressno) {
            this.shipexpressno = shipexpressno;
        }
        public String getShipexpressno() {
            return shipexpressno;
        }

        public void setShipmobile(String shipmobile) {
            this.shipmobile = shipmobile;
        }
        public String getShipmobile() {
            return shipmobile;
        }

        public void setShipdetail(String shipdetail) {
            this.shipdetail = shipdetail;
        }
        public String getShipdetail() {
            return shipdetail;
        }

        public void setRefundtime(String refundtime) {
            this.refundtime = refundtime;
        }
        public String getRefundtime() {
            return refundtime;
        }

        public void setUserDel(String userDel) {
            this.user_del = userDel;
        }
        public String getUserDel() {
            return user_del;
        }

    }
}
