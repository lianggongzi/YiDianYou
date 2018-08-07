package com.duobao.yidianyou.view;

import com.duobao.yidianyou.base.BaseView;
import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;

/**
 * Created by Administrator on 2018-07-05.
 */
public interface SweepView extends BaseView{
    void getSweep(SweepBean sweepBean);
    void getPass(ReturnBean returnBean);
    void getError(String msg);
}
