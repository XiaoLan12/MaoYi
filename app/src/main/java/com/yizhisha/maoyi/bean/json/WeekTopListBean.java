package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by 小蓝 on 2018/2/6.
 */

public class WeekTopListBean {
    private List<WeekTopBean> list;

    public List<WeekTopBean> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "WeekTopListBean{" +
                "list=" + list +
                '}';
    }

    public class WeekTopBean {

        private String spc_id;
        private String spc_name;
        private String spc_litpic;

        public String getSpc_id() {
            return spc_id;
        }

        public void setSpc_id(String spc_id) {
            this.spc_id = spc_id;
        }

        public String getSpc_name() {
            return spc_name;
        }

        public void setSpc_name(String spc_name) {
            this.spc_name = spc_name;
        }

        public String getSpc_litpic() {
            return spc_litpic;
        }

        public void setSpc_litpic(String spc_litpic) {
            this.spc_litpic = spc_litpic;
        }

        @Override
        public String toString() {
            return "WeekTopBean{" +
                    "spc_id='" + spc_id + '\'' +
                    ", spc_name='" + spc_name + '\'' +
                    ", spc_litpic='" + spc_litpic + '\'' +
                    '}';
        }
    }
}
