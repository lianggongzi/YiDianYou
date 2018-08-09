package com.duobao.yidianyou.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2018\8\9 0009.

 * @author yj
 * 简易的加载过渡工具
 * 动画显示过程中不允许用户手动取消
 * 只能通过代码取消
 * 经过测试,context不允许设置为application的context,
 */


public class ProgressDialogUtil {
    private static ProgressDialog mDialog;

    private ProgressDialogUtil() {}

    /**
     * 显示
     * @param title
     * @param msg
     */
    public static void show(Context context, String title, String msg) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new ProgressDialog(context);
        // 点击back键和点击屏幕不隐藏
        mDialog.setCancelable(false);
        mDialog.setTitle(title);
        mDialog.setMessage(msg);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 显示
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        show(context, "", msg);
    }

    /**
     * 隐藏
     */
    public static void dismiss() {
        if (mDialog == null) {
            return;
        }

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
