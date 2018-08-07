package com.duobao.yidianyou.bean;

/**
 * Created by Administrator on 2018-07-17.
 */
public class ReturnBean {

    /**
     * status : 0
     * msg : 失败的原因
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReturnBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
