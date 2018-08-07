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
 * Created by Administrator on 2018-07-07.
 * 修改设备信息页面---暂没用
 */

public class ModifyEquipmentActivity extends AppCompatActivity {

    @BindView(R.id.modify_equipment_jihao_edt)
    ClearWriteEditText modifyEquipmentJihaoEdt;
    @BindView(R.id.modify_equipment_account_edt)
    ClearWriteEditText modifyEquipmentAccountEdt;
    @BindView(R.id.modify_equipment_password_edt)
    ClearWriteEditText modifyEquipmentPasswordEdt;
    @BindView(R.id.modify_equipment_password1_edt)
    ClearWriteEditText modifyEquipmentPassword1Edt;
    @BindView(R.id.modify_equipment_btn)
    Button modifyEquipmentBtn;

    String jihao;
    String account;
    String password;
    String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_equipment);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        modifyEquipmentJihaoEdt.setText((String) SPUtils.get(this, "equipmentJihao", ""));
        modifyEquipmentJihaoEdt.setSelection(modifyEquipmentJihaoEdt.getText().length());
        modifyEquipmentAccountEdt.setText((String) SPUtils.get(this, "equipmentAccount", ""));
    }

    @OnClick(R.id.modify_equipment_btn)
    public void onViewClicked() {
        jihao = modifyEquipmentJihaoEdt.getText().toString().trim();
        account = modifyEquipmentAccountEdt.getText().toString().trim();
        password = modifyEquipmentPasswordEdt.getText().toString().trim();
        password2 = modifyEquipmentPassword1Edt.getText().toString().trim();
        if (TextUtils.isEmpty(jihao)) {
            Toast.makeText(this, "机号不能为空", Toast.LENGTH_SHORT).show();
            modifyEquipmentJihaoEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
            modifyEquipmentAccountEdt.setShakeAnimation();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            modifyEquipmentPasswordEdt.setShakeAnimation();
            return;
        }
        if (password.equals(password2)) {
            SPUtils.put(this, "equipmentJihao", jihao);
            SPUtils.put(this, "equipmentAccount", account);
            SPUtils.put(this, "equipmentPassword", password2);
            SPUtils.put(this, "equipmentIsLogin", true);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this,MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "密码不一样", Toast.LENGTH_SHORT).show();
            modifyEquipmentPassword1Edt.setShakeAnimation();
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
