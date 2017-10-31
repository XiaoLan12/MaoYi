package com.yizhisha.maoyi.bean.json;

import java.util.Date;
import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class ShopcartBean {
    private int mzw_uid;

    private String company;

    private List<Goods> goods ;

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public class Goods {

        private String sid;
        private Date detail;
        private String amount;
        private String gid;
        private String title;
        private String litpic;
        private String price;
        public void setSid(String sid) {
            this.sid = sid;
        }
        public String getSid() {
            return sid;
        }

        public void setDetail(Date detail) {
            this.detail = detail;
        }
        public Date getDetail() {
            return detail;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
        public String getAmount() {
            return amount;
        }

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

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
        public String getLitpic() {
            return litpic;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getPrice() {
            return price;
        }

    }
}
