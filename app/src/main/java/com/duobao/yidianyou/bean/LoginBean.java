package com.duobao.yidianyou.bean;

/**
 * Created by Administrator on 2018-07-05.
 */
public class LoginBean {

    /**
     * msg : 登录成功
     * data : {"userid":247}
     * status : 1
     */

    private String msg;
    private DataBean data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * userid : 247
         */

        private int userid;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
