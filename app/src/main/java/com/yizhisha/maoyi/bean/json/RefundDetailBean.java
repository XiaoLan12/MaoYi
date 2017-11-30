package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class RefundDetailBean {
    private Order order;
    private List<Goods> goods;
    private Refund refund;
    private List<Log> log;
    private String status;
    private String info;

    public Order getOrder() {
        return order;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public Refund getRefund() {
        return refund;
    }

    public List<Log> getLog() {
        return log;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public class Order {

        private int id;
        private String orderno;
        private String status;
        private int amount;
        private double totalprice;
        private long addtime;
        private String address;
        private int address_id;
        private String payorderno;
        private String express_no;
        private int express_id;
        private int refundstatus;
        private String refundno;

        public int getId() {
            return id;
        }

        public String getOrderno() {
            return orderno;
        }

        public String getStatus() {
            return status;
        }

        public int getAmount() {
            return amount;
        }

        public double getTotalprice() {
            return totalprice;
        }

        public long getAddtime() {
            return addtime;
        }

        public String getAddress() {
            return address;
        }

        public int getAddress_id() {
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

        public String getRefundno() {
            return refundno;
        }
    }
    public class Goods {

        private int gid;
        private String title;
        private String price;
        private int amount;
        private double totalprice;
        private String litpic;
        private String detail;
        private String remark;

        public int getGid() {
            return gid;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }

        public int getAmount() {
            return amount;
        }

        public double getTotalprice() {
            return totalprice;
        }

        public String getLitpic() {
            return litpic;
        }

        public String getDetail() {
            return detail;
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

        public int getId() {
            return id;
        }

        public String getRefundno() {
            return refundno;
        }

        public String getOrderno() {
            return orderno;
        }

        public int getUid() {
            return uid;
        }

        public int getType() {
            return type;
        }

        public String getReason() {
            return reason;
        }

        public String getDetail() {
            return detail;
        }

        public String getPic() {
            return pic;
        }

        public String getMoney() {
            return money;
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

        public String getRejectreason() {
            return rejectreason;
        }

        public String getShipexpressid() {
            return shipexpressid;
        }

        public String getShipexpressno() {
            return shipexpressno;
        }

        public String getShipmobile() {
            return shipmobile;
        }

        public String getShipdetail() {
            return shipdetail;
        }

        public long getRefundtime() {
            return refundtime;
        }

        public String getUser_del() {
            return user_del;
        }
    }
    public class Log {

        private String log_id;
        private String log_orderno;
        private String log_refundno;
        private String log_refundstatus;
        private String log_type;
        private String log_refund;
        private String log_detail;
        private String log_addtime;

        public String getLog_id() {
            return log_id;
        }

        public String getLog_orderno() {
            return log_orderno;
        }

        public String getLog_refundno() {
            return log_refundno;
        }

        public String getLog_refundstatus() {
            return log_refundstatus;
        }

        public String getLog_type() {
            return log_type;
        }

        public String getLog_refund() {
            return log_refund;
        }

        public String getLog_detail() {
            return log_detail;
        }

        public String getLog_addtime() {
            return log_addtime;
        }
    }
}
