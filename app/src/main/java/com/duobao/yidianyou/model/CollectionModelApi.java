package com.duobao.yidianyou.model;

import com.duobao.yidianyou.bean.CollectionBean;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-18.
 */
public interface CollectionModelApi {
    interface onFinishListener{
        void onCollectionFinished(CollectionBean collectionBean);
        void onError(String msg);
    }
    void getCollectionData(FormBody body, onFinishListener listener);
}
