package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.bean.CollectionBean;
import com.duobao.yidianyou.model.CollectionModelApi;
import com.duobao.yidianyou.model.CollectionModelImpl;
import com.duobao.yidianyou.view.CollectionView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-18.
 */
public class CollectionPresenterImpl implements CollectionPresenterApi, CollectionModelApi.onFinishListener {

    CollectionView collectionView;
    CollectionModelApi model;

    public CollectionPresenterImpl() {
      this.  model = new CollectionModelImpl();
    }

    @Override
    public void getCollectionBoby(FormBody body) {
        if (collectionView != null) {
            model.getCollectionData(body, this);
        }
    }

    @Override
    public void attachView(CollectionView view) {
        collectionView = view;
    }

    @Override
    public void detachView() {
        collectionView = null;
    }

    @Override
    public void onCollectionFinished(CollectionBean collectionBean) {
        if (collectionView != null) {
            collectionView.getColltion(collectionBean);
        }
    }

    @Override
    public void onError(String msg) {
        if (collectionView != null) {
            collectionView.getError(msg);
        }
    }
}
