package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.bean.LoginBean;
import com.duobao.yidianyou.model.LoginModelApi;
import com.duobao.yidianyou.model.LoginModelmpl;
import com.duobao.yidianyou.view.LoginView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public class LoginPresenterImpl implements LoginModelApi.onFinishListener, LoginPresenterApi {

    LoginView loginView;
    LoginModelApi modelApi;

    public LoginPresenterImpl() {
        this.modelApi = new LoginModelmpl();
    }

    @Override
    public void getLogin(FormBody body) {
        if (loginView != null) {
            modelApi.getLoginData(body, this);
        }
    }

    @Override
    public void attachView(LoginView view) {
        loginView = view;
    }

    @Override
    public void detachView() {
        loginView = null;
    }

    @Override
    public void onLoginFinished(LoginBean loginBean) {

        if (loginView != null) {
            loginView.getLogin(loginBean);
        }
    }

    @Override
    public void onError(String msg) {
        if (loginView != null) {
            loginView.getError(msg);
        }
    }
}
