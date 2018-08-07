package com.duobao.yidianyou.bean;

/**
 * Created by Administrator on 2018-07-18.
 */
public class CollectionBean {

    /**
     * msg : 成功
     * data : null
     * status : 1
     */

    private String msg;
    private Object data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CollectionBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
