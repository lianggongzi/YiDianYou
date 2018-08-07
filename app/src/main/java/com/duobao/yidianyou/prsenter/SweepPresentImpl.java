package com.duobao.yidianyou.prsenter;

import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;
import com.duobao.yidianyou.model.SweepModelApi;
import com.duobao.yidianyou.model.SweepModelImpl;
import com.duobao.yidianyou.view.SweepView;

import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 */
public class SweepPresentImpl implements SweepPresentAPi, SweepModelApi.onFinishListener {

    SweepView sweepView;
    SweepModelApi modelApi;

    public SweepPresentImpl() {
        this.modelApi = new SweepModelImpl();
    }

    @Override
    public void getSweep(FormBody formBody) {
        if (sweepView != null) {
            modelApi.getSweepData(formBody, this);
        }
    }
    @Override
    public void getPass(FormBody formBody) {
        if (sweepView != null) {
            modelApi.getPassData(formBody, this);
        }
    }


    @Override
    public void attachView(SweepView view) {
        sweepView = view;
    }

    @Override
    public void detachView() {
        sweepView = null;
    }

    @Override
    public void onSweepFinished(SweepBean sweepBean) {
        if (sweepView != null) {
            sweepView.getSweep(sweepBean);
        }
    }

    @Override
    public void onPassFinished(ReturnBean returnBean) {
        if (sweepView != null) {
            sweepView.getPass(returnBean);
        }
    }

    @Override
    public void onError(String msg) {
        if (sweepView != null) {
            sweepView.getError(msg);
        }
    }
}
