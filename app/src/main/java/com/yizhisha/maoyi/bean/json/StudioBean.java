package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class StudioBean {
    private String status;
    private String info;
    private List<StudioListBean> workshop;
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

    public void setWorkshop(List<StudioListBean> workshop) {
        this.workshop = workshop;
    }
    public List<StudioListBean> getWorkshop() {
        return workshop;
    }
    public class StudioListBean {

        private int id;
        private String workshop;
        private String avatar;
        private String cover;
        private String background;
        private String linkman;
        private String tel;
        private String mobile;
        private String email;
        private String address;
        private String major;
        private String album;
        private String content;
        private String likekey;
        private String hits;
        private String favs;
        private String addtime;
        private String deltime;
        private String retime;
        private String is_del;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setWorkshop(String workshop) {
            this.workshop = workshop;
        }
        public String getWorkshop() {
            return workshop;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
        public String getAvatar() {
            return avatar;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
        public String getCover() {
            return cover;
        }

        public void setBackground(String background) {
            this.background = background;
        }
        public String getBackground() {
            return background;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
        public String getLinkman() {
            return linkman;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
        public String getTel() {
            return tel;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getMobile() {
            return mobile;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setMajor(String major) {
            this.major = major;
        }
        public String getMajor() {
            return major;
        }


        public void setAlbum(String album) {
            this.album = album;
        }
        public String getAlbum() {
            return album;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setLikekey(String likekey) {
            this.likekey = likekey;
        }
        public String getLikekey() {
            return likekey;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }
        public String getHits() {
            return hits;
        }

        public void setFavs(String favs) {
            this.favs = favs;
        }
        public String getFavs() {
            return favs;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
        public String getAddtime() {
            return addtime;
        }

        public void setDeltime(String deltime) {
            this.deltime = deltime;
        }
        public String getDeltime() {
            return deltime;
        }

        public void setRetime(String retime) {
            this.retime = retime;
        }
        public String getRetime() {
            return retime;
        }

        public void setIsDel(String isDel) {
            this.is_del = isDel;
        }
        public String getIsDel() {
            return is_del;
        }

    }
}
