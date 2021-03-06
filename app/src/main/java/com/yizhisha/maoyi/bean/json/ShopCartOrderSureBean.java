package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/14.
 */

public class ShopCartOrderSureBean {
    private String status;

    private String info;
    private List<Shopcart> shopcart;
    private List<Address> address;
    private String realprice;

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }


    public List<Shopcart> getShopcart() {
        return shopcart;
    }

    public void setShopcart(List<Shopcart> shopcart) {
        this.shopcart = shopcart;
    }

    public List<Address> getAddress() {
        return address;
    }

    public String getRealprice() {
        return realprice;
    }

    public class Shopcart {
        private String id;

        private String gid;

        private String amount;

        private String detail;

        private String price;

        private String litpic;

        private String title;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setGid(String gid){
            this.gid = gid;
        }
        public String getGid(){
            return this.gid;
        }
        public void setAmount(String amount){
            this.amount = amount;
        }
        public String getAmount(){
            return this.amount;
        }
        public void setDetail(String detail){
            this.detail = detail;
        }
        public String getDetail(){
            return this.detail;
        }
        public void setPrice(String price){
            this.price = price;
        }
        public String getPrice(){
            return this.price;
        }
        public void setLitpic(String litpic){
            this.litpic = litpic;
        }
        public String getLitpic(){
            return this.litpic;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }

    }

    public class Address {

        private int id;
        private int uid;
        private String linkman;
        private String mobile;
        private String area_pid;
        private String area_sid;
        private String area_pname;
        private String area_sname;
        private String address;
        private String zipcode;
        private String index;
        private String area_app;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getArea_pid() {
            return area_pid;
        }

        public void setArea_pid(String area_pid) {
            this.area_pid = area_pid;
        }

        public String getArea_sid() {
            return area_sid;
        }

        public void setArea_sid(String area_sid) {
            this.area_sid = area_sid;
        }

        public String getArea_pname() {
            return area_pname;
        }

        public void setArea_pname(String area_pname) {
            this.area_pname = area_pname;
        }

        public String getArea_sname() {
            return area_sname;
        }

        public void setArea_sname(String area_sname) {
            this.area_sname = area_sname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getArea_app() {
            return area_app;
        }

        public void setArea_app(String area_app) {
            this.area_app = area_app;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
