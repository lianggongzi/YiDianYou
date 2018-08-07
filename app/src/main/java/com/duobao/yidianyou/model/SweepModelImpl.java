package com.duobao.yidianyou.model;

import android.support.annotation.NonNull;

import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;
import com.duobao.yidianyou.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public class SweepModelImpl implements SweepModelApi {
    @Override
    public void getSweepData(FormBody body, final onFinishListener listener) {
        RetrofitUtil.getApi().getCardno(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<SweepBean>() {
                    @Override
                    public void onNext(@NonNull SweepBean sweepBean) {
                        listener.onSweepFinished(sweepBean);
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

    @Override
    public void getPassData(FormBody body, final onFinishListener listener) {
        RetrofitUtil.getApi().getPass(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ReturnBean>() {
                    @Override
                    public void onNext(@NonNull ReturnBean returnBean) {
                        listener.onPassFinished(returnBean);
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
