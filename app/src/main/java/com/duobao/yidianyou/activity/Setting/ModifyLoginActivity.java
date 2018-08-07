package com.duobao.yidianyou.activity.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-03.
 * 修改登陆信息页面---暂没用
 */

public class ModifyLoginActivity extends AppCompatActivity {

    @BindView(R.id.modify_account_edt)
    ClearWriteEditText modifyAccountEdt;
    @BindView(R.id.modify_password_edt)
    ClearWriteEditText modifyPasswordEdt;
    @BindView(R.id.modify_password2_edt)
    ClearWriteEditText modifyPassword2Edt;
    @BindView(R.id.modify_btn)
    Button modifyBtn;

    String account;
    String password;
    String password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        modifyAccountEdt.setText((String)SPUtils.get(this,"account",""));
        modifyAccountEdt.setSelection(modifyAccountEdt.getText().length());
    }

    @OnClick(R.id.modify_btn)
    public void onViewClicked() {
        account = modifyAccountEdt.getText().toString().trim();
        password = modifyPasswordEdt.getText().toString().trim();
        password2=modifyPassword2Edt.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
            modifyAccountEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            modifyPasswordEdt.setShakeAnimation();
            return;
        }
        if (password.equals(password2)){
            SPUtils.put(this, "account", account);
            SPUtils.put(this, "password", password2);
            SPUtils.put(this, "isLogin", true);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else {
            Toast.makeText(this, "密码不一样", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
