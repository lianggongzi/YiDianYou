package com.duobao.yidianyou.model;

import com.duobao.yidianyou.bean.LoginBean;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public interface LoginModelApi  {
     interface onFinishListener{
        void onLoginFinished(LoginBean loginBean);
        void onError(String msg);
    }
    void getLoginData(FormBody body, onFinishListener listener);
}
