package com.yizhisha.maoyi.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class GoodsListBean  implements Serializable {
        private List<Address> address;
        private String status;
        private String info;
        public void setAddress(List<Address> address) {
            this.address = address;
        }
        public List<Address> getAddress() {
            return address;
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
    public class Address  implements Serializable {

        private int id;
        private int uid;
        private String linkman;
        private String area_pname;
        private String area_sname;
        private String mobile;
        private String address;
        private String area_app;
        private String index;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
        public int getUid() {
            return uid;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
        public String getLinkman() {
            return linkman;
        }

        public void setArea_pname(String area_pname) {
            this.area_pname = area_pname;
        }
        public String getArea_pname() {
            return area_pname;
        }

        public void setArea_sname(String area_sname) {
            this.area_sname = area_sname;
        }
        public String getArea_sname() {
            return area_sname;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getMobile() {
            return mobile;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setArea_app(String area_app) {
            this.area_app = area_app;
        }
        public String getArea_app() {
            return area_app;
        }

        public void setIndex(String index) {
            this.index = index;
        }
        public String getIndex() {
            return index;
        }

    }
}
