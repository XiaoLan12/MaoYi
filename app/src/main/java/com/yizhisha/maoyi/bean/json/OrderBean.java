package com.yizhisha.maoyi.bean.json;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class OrderBean {
    private String id;
    private String orderno;
    private String status;
    private String amount;
    private String totalprice;
    private String addtime;
    private Address address;
    private String addressId;
    private List<Goods> goods;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
    public String getOrderno() {
        return orderno;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
    public String getTotalprice() {
        return totalprice;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
    public String getAddtime() {
        return addtime;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    public String getAddressId() {
        return addressId;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
    public List<Goods> getGoods() {
        return goods;
    }
    public class Address {

        private String uid;
        private String linkman;
        private String mobile;
        private String areaPname;
        private String areaSname;
        private String address;
        private String areaApp;
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
            this.areaPname = areaPname;
        }
        public String getAreaPname() {
            return areaPname;
        }

        public void setAreaSname(String areaSname) {
            this.areaSname = areaSname;
        }
        public String getAreaSname() {
            return areaSname;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setAreaApp(String areaApp) {
            this.areaApp = areaApp;
        }
        public String getAreaApp() {
            return areaApp;
        }

    }

    public class Goods {

        private String gid;
        private String title;
        private String price;
        private String amount;
        private String totalprice;
        private String litpic;
        private Date detail;
        private String remark;
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

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }
        public String getTotalprice() {
            return totalprice;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
        public String getLitpic() {
            return litpic;
        }

        public void setDetail(Date detail) {
            this.detail = detail;
        }
        public Date getDetail() {
            return detail;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
        public String getRemark() {
            return remark;
        }

    }
}
