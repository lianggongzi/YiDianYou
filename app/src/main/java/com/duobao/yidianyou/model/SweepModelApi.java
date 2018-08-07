package com.duobao.yidianyou.model;

import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;

import okhttp3.FormBody;


/**
 * Created by Administrator on 2018-07-05.
 */
public interface SweepModelApi {
    interface onFinishListener{
        void onSweepFinished(SweepBean sweepBean);
        void onPassFinished(ReturnBean returnBean);
        void onError(String msg);
    }
    void getSweepData(FormBody body, onFinishListener listener);
    void getPassData(FormBody body, onFinishListener listener);
}
