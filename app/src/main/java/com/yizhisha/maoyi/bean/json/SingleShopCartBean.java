package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class SingleShopCartBean {
    private Goods goods;
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    public Goods getGoods() {
        return goods;
    }
    public class Goods {

        private String title;
        private String litpic;
        private String price;
        private List<String> style;
        private boolean shopcart;
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

        public void setStyle(List<String> style) {
            this.style = style;
        }
        public List<String> getStyle() {
            return style;
        }

        public void setShopcart(boolean shopcart) {
            this.shopcart = shopcart;
        }
        public boolean getShopcart() {
            return shopcart;
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

    }
}
