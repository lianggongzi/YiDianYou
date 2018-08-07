package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.base.BasePresenter;
import com.duobao.yidianyou.view.SweepView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public interface SweepPresentAPi  extends BasePresenter<SweepView>{
    void getSweep(FormBody formBody);
    void getPass(FormBody formBody);

}
