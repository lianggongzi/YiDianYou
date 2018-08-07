package com.duobao.yidianyou.view;

import com.duobao.yidianyou.base.BaseView;
import com.duobao.yidianyou.bean.CollectionBean;

/**
 * Created by Administrator on 2018-07-18.
 */
public interface CollectionView extends BaseView{
    void getColltion(CollectionBean collectionBean);
    void getError(String msg);
}
