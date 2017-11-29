package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class RefundListBean {
    private String id;
    private String orderno;
    private String status;
    private String amount;
    private String totalprice;
    private String addtime;
    private String address;
    private String address_id;
    private String payorderno;
    private String express_no;
    private String express_id;
    private int refundstatus;
    private String refundno;
    private List<Goods> goods;
    private Refund refund;

    public String getId() {
        return id;
    }

    public String getOrderno() {
        return orderno;
    }

    public String getStatus() {
        return status;
    }

    public String getAmount() {
        return amount;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getAddress() {
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

    public String getExpress_id() {
        return express_id;
    }

    public int getRefundstatus() {
        return refundstatus;
    }

    public String getRefundno() {
        return refundno;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public Refund getRefund() {
        return refund;
    }

    public class Goods {
        private int gid;
        private String title;
        private double price;
        private int amount;
        private double totalprice;
        private String litpic;
        private String detail;
        private String remark;
        public void setGid(int gid) {
            this.gid = gid;
        }
        public int getGid() {
            return gid;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        public double getPrice() {
            return price;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
        public int getAmount() {
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

    }
    public class Refund {
        private int id;
        private String refundno;
        private String orderno;
        private int uid;
        private int type;
        private String reason;
        private String detail;
        private String pic;
        private String money;
        private int refundstatus;
        private long addtime;
        private long replytime;
        private long starttime;
        private long shiptime;
        private long receivetime;
        private String rejectreason;
        private String shipexpressid;
        private String shipexpressno;
        private String shipmobile;
        private String shipdetail;
        private long refundtime;
        private String user_del;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
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

        public void setUid(int uid) {
            this.uid = uid;
        }
        public int getUid() {
            return uid;
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

        public void setUserDel(String userDel) {
            this.user_del = userDel;
        }
        public String getUserDel() {
            return user_del;
        }

        public int getType() {
            return type;
        }

        public int getRefundstatus() {
            return refundstatus;
        }

        public long getAddtime() {
            return addtime;
        }

        public long getReplytime() {
            return replytime;
        }

        public long getStarttime() {
            return starttime;
        }

        public long getShiptime() {
            return shiptime;
        }

        public long getReceivetime() {
            return receivetime;
        }

        public long getRefundtime() {
            return refundtime;
        }

        public String getUser_del() {
            return user_del;
        }
    }
}
