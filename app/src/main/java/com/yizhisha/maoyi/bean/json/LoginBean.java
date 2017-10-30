package com.yizhisha.maoyi.bean.json;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class LoginBean {


        private String status;
        private String info;
        private int uid;
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

        public void setUid(int uid) {
            this.uid = uid;
        }
        public int getUid() {
            return uid;
        }
}
