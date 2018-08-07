package com.duobao.yidianyou.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time  : 2018-01-02
 *     desc  : 吐司工具类
 *     version: 1.0
 * </pre>
 */

public class ToastUtil {

    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
