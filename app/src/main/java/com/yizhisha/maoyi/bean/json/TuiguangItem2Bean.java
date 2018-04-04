package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by 小蓝 on 2018/4/1.
 */

public class TuiguangItem2Bean {
    private List<Info> info ;

    private String status;

    private String url;

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class Info {

        private String uid;

        private String nickname;

        private int sex;

        private String mobile;

        private long regtime;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getRegtime() {
            return regtime;
        }

        public void setRegtime(long regtime) {
            this.regtime = regtime;
        }
    }
}
