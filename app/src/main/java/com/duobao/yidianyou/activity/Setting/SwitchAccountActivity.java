package com.duobao.yidianyou.activity.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-07.
 * 切换收款核销员账号
 */

public class SwitchAccountActivity extends AppCompatActivity {

    @BindView(R.id.switch_rb1)
    RadioButton switchRb1;
    @BindView(R.id.switch_rb2)
    RadioButton switchRb2;
    @BindView(R.id.switch_rb3)
    RadioButton switchRb3;
    @BindView(R.id.switch_rb4)
    RadioButton switchRb4;
    @BindView(R.id.switch_rb5)
    RadioButton switchRb5;
    @BindView(R.id.switch_rg)
    RadioGroup switchRg;
    @BindView(R.id.switch_edt)
    ClearWriteEditText switchEdt;
    @BindView(R.id.switch_btn)
    Button switchBtn;

    String rb;
    String AccountName1;
    String AccountName2;
    String AccountName3;
    String AccountName4;
    String AccountName5;
    String switchAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        ButterKnife.bind(this);
        initView();
        switchRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.switch_rb1:
                        rb = switchRb1.getText().toString();
                        break;
                    case R.id.switch_rb2:
                        rb = switchRb2.getText().toString();
                        break;
                    case R.id.switch_rb3:
                        rb = switchRb3.getText().toString();
                        break;
                    case R.id.switch_rb4:
                        rb = switchRb4.getText().toString();
                        break;
                    case R.id.switch_rb5:
                        rb = switchRb5.getText().toString();
                        break;
                }
            }
        });
    }

    private void initView() {
        AccountName1 = (String) SPUtils.get(this, "AccountName1", "");
        AccountName2 = (String) SPUtils.get(this, "AccountName2", "");
        AccountName3 = (String) SPUtils.get(this, "AccountName3", "");
        AccountName4 = (String) SPUtils.get(this, "AccountName4", "");
        AccountName5 = (String) SPUtils.get(this, "AccountName5", "");
        switchAccount = (String) SPUtils.get(this, "switchAccount", "");
        if (AccountName1.equals("")) {
            switchRb1.setVisibility(View.GONE);
        } else {
            switchRb1.setVisibility(View.VISIBLE);
            switchRb1.setText(AccountName1);
            if (switchAccount.equals(AccountName1)) {
                switchRb1.setChecked(true);
            }
        }
        if (AccountName2.equals("")) {
            switchRb2.setVisibility(View.GONE);
        } else {
            switchRb2.setVisibility(View.VISIBLE);
            switchRb2.setText(AccountName2);
            if (switchAccount.equals(AccountName2)) {
                switchRb2.setChecked(true);
            }
        }
        if (AccountName3.equals("")) {
            switchRb3.setVisibility(View.GONE);
        } else {
            switchRb3.setVisibility(View.VISIBLE);
            switchRb3.setText(AccountName3);
            if (switchAccount.equals(AccountName3)) {
                switchRb3.setChecked(true);
            }
        }
        if (AccountName4.equals("")) {
            switchRb4.setVisibility(View.GONE);
        } else {
            switchRb4.setVisibility(View.VISIBLE);
            switchRb4.setText(AccountName4);
            if (switchAccount.equals(AccountName4)) {
                switchRb4.setChecked(true);
            }
        }
        if (AccountName5.equals("")) {
            switchRb5.setVisibility(View.GONE);
        } else {
            switchRb5.setVisibility(View.VISIBLE);
            switchRb5.setText(AccountName5);
            if (switchAccount.equals(AccountName5)) {
                switchRb5.setChecked(true);
            }
        }
    }

    @OnClick(R.id.switch_btn)
    public void onViewClicked() {
        String AccountNamePaw1 = (String) SPUtils.get(this, "AccountNamePaw1", "");
        String AccountNamePaw2 = (String) SPUtils.get(this, "AccountNamePaw2", "");
        String AccountNamePaw3 = (String) SPUtils.get(this, "AccountNamePaw3", "");
        String AccountNamePaw4 = (String) SPUtils.get(this, "AccountNamePaw4", "");
        String AccountNamePaw5 = (String) SPUtils.get(this, "AccountNamePaw5", "");
        Log.d("feng",AccountNamePaw1+"---"+AccountNamePaw2+"---"+AccountNamePaw3+"---"+AccountNamePaw4+"---"+AccountNamePaw5);
        Log.d("feng", switchRb1.isChecked() + "---" + switchRb2.isChecked() + "---" + switchRb3.isChecked() + "---" + switchRb4.isChecked() + "---" + switchRb5.isChecked());

        if (switchRb1.isChecked() == true) {
            if (switchEdt.getText().toString().equals(AccountNamePaw1)) {
                SPUtils.put(this, "switchAccount", rb);//保存当前收款核销员的名字
                ToastUtil.showToast(this, "切换成功");
                finish();
            } else {
                ToastUtil.showToast(this, "请输入正确的密码");
                switchEdt.setShakeAnimation();
            }
        } else if (switchRb2.isChecked() == true) {
            if (switchEdt.getText().toString().equals(AccountNamePaw2)) {
                SPUtils.put(this, "switchAccount", rb);//保存当前收款核销员的名字
                ToastUtil.showToast(this, "切换成功");
                finish();
            } else {
                ToastUtil.showToast(this, "请输入正确的密码");
                switchEdt.setShakeAnimation();
            }
        } else if (switchRb3.isChecked() == true) {
            if (switchEdt.getText().toString().equals(AccountNamePaw3)) {
                SPUtils.put(this, "switchAccount", rb);//保存当前收款核销员的名字
                ToastUtil.showToast(this, "切换成功");
                finish();
            } else {
                ToastUtil.showToast(this, "请输入正确的密码");
                switchEdt.setShakeAnimation();
            }
        } else if (switchRb4.isChecked() == true) {
            if (switchEdt.getText().toString().equals(AccountNamePaw4)) {
                SPUtils.put(this, "switchAccount", rb);//保存当前收款核销员的名字
                ToastUtil.showToast(this, "切换成功");
                finish();
            } else {
                ToastUtil.showToast(this, "请输入正确的密码");
                switchEdt.setShakeAnimation();
            }
        } else if (switchRb5.isChecked() == true) {
            if (switchEdt.getText().toString().equals(AccountNamePaw5)) {
                SPUtils.put(this, "switchAccount", rb);//保存当前收款核销员的名字
                ToastUtil.showToast(this, "切换成功");
                finish();
            } else {
                ToastUtil.showToast(this, "请输入正确的密码");
                switchEdt.setShakeAnimation();
            }
        }

    }
}
