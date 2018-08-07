package com.duobao.yidianyou.view;

import com.duobao.yidianyou.base.BaseView;
import com.duobao.yidianyou.bean.LoginBean;

/**
 * Created by Administrator on 2018-07-05.
 */
public interface LoginView extends BaseView{
   void getLogin(LoginBean loginBean);
    void getError(String msg);
}
