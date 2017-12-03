package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3 0003.
 */

public class SingleShoppGoodBean {
    private String title;
    private String litpic;
    private String price;
    private Shopcart shopcart;
    private List<Style> style;
    private String status;
    private String info;
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

    public void setShopcart(Shopcart shopcart) {
        this.shopcart = shopcart;
    }
    public Shopcart getShopcart() {
        return shopcart;
    }

    public void setStyle(List<Style> style) {
        this.style = style;
    }
    public List<Style> getStyle() {
        return style;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public class Shopcart {

        private String amount;
        private String detail;
        public void setAmount(String amount) {
            this.amount = amount;
        }
        public String getAmount() {
            return amount;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
        public String getDetail() {
            return detail;
        }

    }
    public class Style {

        private String color;
        private String size;
        public void setColor(String color) {
            this.color = color;
        }
        public String getColor() {
            return color;
        }

        public void setSize(String size) {
            this.size = size;
        }
        public String getSize() {
            return size;
        }

    }
}
