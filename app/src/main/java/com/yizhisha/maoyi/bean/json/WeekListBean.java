package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WeekListBean {
    private List<WeekBean> list;
    private String status;
    private String info;

    public List<WeekBean> getList() {
        return list;
    }

    public void setList(List<WeekBean> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public class WeekBean{
        private int id;
        private String  title;
        private String litpic;
        private String price;
        private String sales;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        @Override
        public String toString() {
            return "WeekListBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", litpic='" + litpic + '\'' +
                    ", price='" + price + '\'' +
                    ", sales='" + sales + '\'' +
                    '}';
        }
    }

}
