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

        private int sid;
        private String detail;
        private int amount;
        private int gid;
        private String title;
        private String litpic;
        private float price;
        public void setSid(int sid) {
            this.sid = sid;
        }
        public int getSid() {
            return sid;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
        public String getDetail() {
            return detail;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
        public int getAmount() {
            return amount;
        }

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

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
        public String getLitpic() {
            return litpic;
        }

        public void setPrice(float price) {
            this.price = price;
        }
        public float getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "sid=" + sid +
                    ", detail='" + detail + '\'' +
                    ", amount=" + amount +
                    ", gid=" + gid +
                    ", title='" + title + '\'' +
                    ", litpic='" + litpic + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopcartBean{" +
                "mzw_uid=" + mzw_uid +
                ", company='" + company + '\'' +
                ", goods=" + goods +
                '}';
    }
}
