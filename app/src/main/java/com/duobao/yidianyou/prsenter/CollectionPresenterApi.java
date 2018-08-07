package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.base.BasePresenter;
import com.duobao.yidianyou.view.CollectionView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-18.
 */
public interface CollectionPresenterApi extends BasePresenter<CollectionView> {
    void getCollectionBoby(FormBody body);
}
