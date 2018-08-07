package com.duobao.yidianyou.bean;

/**
 * Created by Administrator on 2018-07-04.
 */
public class SweepBean {

    /**
     * status : 1
     * name : 用户姓名
     * dq : 地区
     * yxq : 2018-01-01到2019-01-01
     * cardname : 卡名称
     * sfz : 身份证号码
     * cstatus : 卡状态
     * cardno : 卡号
     * buydate : 2018-05-15
     * money : 单价
     * rz_sm : 1
     * rz_rl : 1
     * logo : 旅游卡图片完整图片
     */

    private int status;
    private String name;
    private String dq;
    private String yxq;
    private String cardname;
    private String sfz;
    private String cstatus;
    private String cardno;
    private String buydate;
    private String money;
    private String rz_sm;
    private String rz_rl;
    private String logo;
    private String msg;
    private String clogo;
    private String rlogo;

    public String getClogo() {
        return clogo;
    }

    public void setClogo(String clogo) {
        this.clogo = clogo;
    }

    public String getRlogo() {
        return rlogo;
    }

    public void setRlogo(String rlogo) {
        this.rlogo = rlogo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getYxq() {
        return yxq;
    }

    public void setYxq(String yxq) {
        this.yxq = yxq;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getBuydate() {
        return buydate;
    }

    public void setBuydate(String buydate) {
        this.buydate = buydate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRz_sm() {
        return rz_sm;
    }

    public void setRz_sm(String rz_sm) {
        this.rz_sm = rz_sm;
    }

    public String getRz_rl() {
        return rz_rl;
    }

    public void setRz_rl(String rz_rl) {
        this.rz_rl = rz_rl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "SweepBean{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", dq='" + dq + '\'' +
                ", yxq='" + yxq + '\'' +
                ", cardname='" + cardname + '\'' +
                ", sfz='" + sfz + '\'' +
                ", cstatus='" + cstatus + '\'' +
                ", cardno='" + cardno + '\'' +
                ", buydate='" + buydate + '\'' +
                ", money='" + money + '\'' +
                ", rz_sm='" + rz_sm + '\'' +
                ", rz_rl='" + rz_rl + '\'' +
                ", logo='" + logo + '\'' +
                ", msg='" + msg + '\'' +
                ", clogo='" + clogo + '\'' +
                ", rlogo='" + rlogo + '\'' +
                '}';
    }
}
