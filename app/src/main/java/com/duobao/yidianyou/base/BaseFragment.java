package com.duobao.yidianyou.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.duobao.yidianyou.utils.ToastUtil;
import com.duobao.yidianyou.widget.ShapeLoadingDialog;

/**
 * Created by HJianFei on 2017/6/29.
 * <p>
 * 描述：基类Fragment的封装
 * 在BaseFragment中进行P和V的初始化绑定
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected Activity mActivity;
    protected T mPresenter;
    private ShapeLoadingDialog sweetAlertDialog;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mWidth;
    protected int mHeight;
    protected float mRatio;
    protected int mAvatarSize;
    private Dialog dialog;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //订阅接收消息,子类只要重写onEvent就能收到消息
//        JMessageClient.registerEventReceiver(this);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mRatio = Math.min((float) mWidth / 720, (float) mHeight / 1280);
        mAvatarSize = (int) (50 * mDensity);
        createPresenter();
        if (mPresenter != null) {

            mPresenter.attachView(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销消息接收
//        JMessageClient.unRegisterEventReceiver(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract void createPresenter();

    @Override
    public void showDialog(String msg) {
        sweetAlertDialog = new ShapeLoadingDialog.Builder(mActivity)
                .loadText(msg)
                .build();
        sweetAlertDialog.show();

    }

    @Override
    public void hideDialog() {
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
        }

    }

    @Override
    public void showError(String msg) {
        showToast(msg);

    }

    @Override
    public void showEmpty() {

    }


    public void showToast(String msg) {
        ToastUtil.showToast(App.getInstance(), msg);
    }
}
