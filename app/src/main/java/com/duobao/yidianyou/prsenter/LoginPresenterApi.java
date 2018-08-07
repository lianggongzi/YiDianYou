package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.base.BasePresenter;
import com.duobao.yidianyou.view.LoginView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public interface LoginPresenterApi  extends BasePresenter<LoginView>{
    void getLogin(FormBody body);
}
