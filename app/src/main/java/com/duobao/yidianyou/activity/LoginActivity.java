package com.duobao.yidianyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.base.BaseActivity;
import com.duobao.yidianyou.bean.LoginBean;
import com.duobao.yidianyou.prsenter.LoginPresenterImpl;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.DesUtils;
import com.duobao.yidianyou.utils.LogUtils;
import com.duobao.yidianyou.utils.MD5Util;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018-07-05.
 * 初次进来登陆
 */
public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginView {
    @BindView(R.id.login_account_edt)
    ClearWriteEditText loginAccountEdt;
    @BindView(R.id.login_password_edt)
    ClearWriteEditText loginPasswordEdt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    String account;
    String password;
    String password2;
    boolean isLogin = false;
    @BindView(R.id.login_password2_edt)
    ClearWriteEditText loginPassword2Edt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //判断之前是否登陆过
        isLogin = (boolean) SPUtils.get(this, "isLogin", false);
        if (isLogin == true) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        initView();
    }

    private void initView() {
        loginAccountEdt.setText((String)SPUtils.get(this,"account",""));
        loginPasswordEdt.setText((String)SPUtils.get(this,"password",""));
        loginPassword2Edt.setText((String)SPUtils.get(this,"password",""));
    }


    @OnClick(R.id.login_btn)
    public void onViewClicked() {

        account = loginAccountEdt.getText().toString().trim();
        password = loginPasswordEdt.getText().toString().trim();
        password2=loginPassword2Edt.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
            loginAccountEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            loginPasswordEdt.setShakeAnimation();
            return;
        }
        if (password.equals(password2)){
            SPUtils.put(this, "account", account);
            SPUtils.put(this, "password", password2);
            SPUtils.put(this, "isLogin", true);
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this, "密码不一样", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenterImpl();
    }

    //参数加密
    public void params() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("rid", "1");
            obj.put("loginnum", "100157");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = obj.toString(); // 参数
        String sign = MD5Util.MD5Encode(obj.toString()); // sign  + "ipassduobao@ipass"
        params = DesUtils.desEncrypt("duobaost", params);
//        SPUtils.put(this, "posnum", posnum);
//        SPUtils.put(this, "loginnum", loginnum);
//        LogUtils.d("feng", params + "----" + sign);
        initData(params, sign);
    }

    //网络访问
    private void initData(String parameter, String sign) {
        FormBody formBody = new FormBody
                .Builder()
                .add("parameter", parameter)
                .add("sign", sign)
                .build();
        mPresenter.getLogin(formBody);
    }

    //返回的数据
    @Override
    public void getLogin(LoginBean loginBean) {
    }

    @Override
    public void getError(String msg) {
        LogUtils.e("feng", msg);
    }
}
