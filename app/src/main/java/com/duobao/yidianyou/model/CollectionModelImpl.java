package com.duobao.yidianyou.model;

import android.support.annotation.NonNull;

import com.duobao.yidianyou.bean.CollectionBean;
import com.duobao.yidianyou.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-18.
 */
public class CollectionModelImpl implements CollectionModelApi {
    @Override
    public void getCollectionData(FormBody body, final onFinishListener listener) {
        RetrofitUtil.getApi().getReture(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<CollectionBean>() {
                    @Override
                    public void onNext(@NonNull CollectionBean collectionBean) {
                        listener.onCollectionFinished(collectionBean);
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
