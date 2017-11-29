package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class RefundDetailBean {
    private Order order;
    private List<Goods> goods;
    private Refund refund;
    private List<String> log;
    private String status;
    private String info;

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
        private String refundstatus;
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
    }
}
