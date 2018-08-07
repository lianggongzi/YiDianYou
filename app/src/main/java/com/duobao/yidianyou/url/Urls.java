package com.duobao.yidianyou.url;

/**
 * Created by Administrator on 2018-07-05.
 * 描述：接口 API URL
 */
public class Urls {


    public static final String API_SERVER = "http://api.daodb.com/";

    /**
     * 收款接口
     */

    public static final String BARCODEPAY = API_SERVER+"api/pay/barcodepay.do";
    /**
     * 1.根据订单号查询对应的用户旅游卡详细信息
     */
    public static final String CARDNO = API_SERVER + "api/ytc/cardno.do?";
    /**
     * 提交旅游卡订单号，更新通行记录
     */
    public static final String PASS = API_SERVER + "api/ytc/pass.do?";

    /**
     * 登陆
     */
    public static final String LOGIN = API_SERVER + "api/ytc/login.do";



}
