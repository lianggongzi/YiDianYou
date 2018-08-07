package com.duobao.yidianyou.model;

import android.support.annotation.NonNull;

import com.duobao.yidianyou.bean.LoginBean;
import com.duobao.yidianyou.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public class LoginModelmpl implements LoginModelApi {
    @Override
    public void getLoginData(FormBody body, final onFinishListener listener) {
        RetrofitUtil.getApi().getLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<LoginBean>() {
                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        listener.onLoginFinished(loginBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
