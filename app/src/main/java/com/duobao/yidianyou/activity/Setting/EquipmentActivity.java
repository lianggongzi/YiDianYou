package com.duobao.yidianyou.activity.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.duobao.yidianyou.activity.MainActivity;
import com.duobao.yidianyou.R;
import com.duobao.yidianyou.activity.LoginActivity;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-12.
 * 初次进来设置设备信息
 */
public class EquipmentActivity extends AppCompatActivity {
    @BindView(R.id.equipment_jihao_edt)
    ClearWriteEditText equipmentJihaoEdt;
    @BindView(R.id.equipment_account_edt)
    ClearWriteEditText equipmentAccountEdt;
    @BindView(R.id.equipment_password_edt)
    ClearWriteEditText equipmentPasswordEdt;
    @BindView(R.id.equipment_password1_edt)
    ClearWriteEditText equipmentPassword1Edt;
    @BindView(R.id.equipment_btn)
    Button equipmentBtn;

    String jihao;
    String account;
    String password;
    String password2;

    boolean isEquipment = false;//判断是否已经设置好设备的信息，false为没有设置好


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        ButterKnife.bind(this);
        isEquipment = (boolean) SPUtils.get(this, "equipmentIsLogin", false);
        if (isEquipment == true) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        equipmentJihaoEdt.setText((String) SPUtils.get(this, "equipmentJihao", ""));
        equipmentJihaoEdt.setSelection(equipmentJihaoEdt.getText().length());
        equipmentAccountEdt.setText((String) SPUtils.get(this, "equipmentAccount", ""));
        equipmentPasswordEdt.setText((String) SPUtils.get(this, "equipmentPassword", ""));
        equipmentPassword1Edt.setText((String) SPUtils.get(this, "equipmentPassword", ""));
    }

    @OnClick(R.id.equipment_btn)
    public void onViewClicked() {
        jihao = equipmentJihaoEdt.getText().toString().trim();
        account = equipmentAccountEdt.getText().toString().trim();
        password = equipmentPasswordEdt.getText().toString().trim();
        password2 = equipmentPassword1Edt.getText().toString().trim();
        if (TextUtils.isEmpty(jihao)) {
            Toast.makeText(this, "机号不能为空", Toast.LENGTH_SHORT).show();
            equipmentJihaoEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
            equipmentAccountEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            equipmentPasswordEdt.setShakeAnimation();
            return;
        }
        if (password.equals(password2)) {
            SPUtils.put(this, "equipmentJihao", jihao);
            SPUtils.put(this, "equipmentAccount", account);
            SPUtils.put(this, "equipmentPassword", password2);
            SPUtils.put(this, "equipmentIsLogin", true);
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
            if ((boolean) SPUtils.get(this, "isLogin", false) == true) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        } else {
            Toast.makeText(this, "密码不一样", Toast.LENGTH_SHORT).show();
            equipmentPassword1Edt.setShakeAnimation();
        }
    }
}
