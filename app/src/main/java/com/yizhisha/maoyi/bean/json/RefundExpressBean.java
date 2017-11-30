package com.yizhisha.maoyi.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class RefundExpressBean {
    private String status;
    private String info;
    private String express_no;
    private Express express;
    private List<Result> result;

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public String getExpress_no() {
        return express_no;
    }

    public Express getExpress() {
        return express;
    }

    public List<Result> getResult() {
        return result;
    }

    public class Express {

        private String exp_name;
        private String exp_sign;
        public void setExpName(String expName) {
            this.exp_name = expName;
        }
        public String getExpName() {
            return exp_name;
        }

        public void setExpSign(String expSign) {
            this.exp_sign = expSign;
        }
        public String getExpSign() {
            return exp_sign;
        }

    }
    public class Result {

        private String time;
        private String status;
        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

    }
}
