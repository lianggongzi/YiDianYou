package com.duobao.yidianyou.api;

import com.duobao.yidianyou.bean.CollectionBean;
import com.duobao.yidianyou.bean.LoginBean;
import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;
import com.duobao.yidianyou.url.Urls;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018-07-05.
 * 描述：各种网络请求接口
 */
public interface Api {
    /**
     * 根据二维码获取旅游卡信息
     *
     * @param body
     * @return
     */
    @POST(Urls.CARDNO)
    Observable<SweepBean> getCardno(@Body RequestBody body);

    /**
     * 提交旅游卡订单号，更新通行记录
     */
    @POST(Urls.PASS)
    Observable<ReturnBean> getPass(@Body RequestBody body);
    /**
     * 登陆
     *
     * @param body
     * @return
     */
    @POST(Urls.LOGIN)
    Observable<LoginBean> getLogin(@Body RequestBody body);

    /**
     * 测试服务器上的收款接口
     * @param body
     * @return
     */
    @POST(Urls.BARCODEPAY)
    Observable<CollectionBean> getReture(@Body RequestBody body);

}
