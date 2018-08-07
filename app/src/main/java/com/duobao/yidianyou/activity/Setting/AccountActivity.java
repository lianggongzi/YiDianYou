package com.duobao.yidianyou.activity.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-07.
 * 设置收款核销员信息
 */


public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.account_name1_edt)
    EditText accountName1Edt;
    @BindView(R.id.account_password1_edt)
    EditText accountPassword1Edt;
    @BindView(R.id.account_name2_edt)
    EditText accountName2Edt;
    @BindView(R.id.account_password2_edt)
    EditText accountPassword2Edt;
    @BindView(R.id.account_name3_edt)
    EditText accountName3Edt;
    @BindView(R.id.account_password3_edt)
    EditText accountPassword3Edt;
    @BindView(R.id.account_name4_edt)
    EditText accountName4Edt;
    @BindView(R.id.account_password4_edt)
    EditText accountPassword4Edt;
    @BindView(R.id.account_name5_edt)
    EditText accountName5Edt;
    @BindView(R.id.account_password5_edt)
    EditText accountPassword5Edt;
    @BindView(R.id.account_btn)
    Button accountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        accountName1Edt.setText((String) SPUtils.get(this, "AccountName1", ""));
        accountName1Edt.setSelection(((String) SPUtils.get(this, "AccountName1", "")).length());
        accountPassword1Edt.setText((String) SPUtils.get(this, "AccountNamePaw1", ""));
        accountName2Edt.setText((String) SPUtils.get(this, "AccountName2", ""));
        accountPassword2Edt.setText((String) SPUtils.get(this, "AccountNamePaw2", ""));
        accountName3Edt.setText((String) SPUtils.get(this, "AccountName3", ""));
        accountPassword3Edt.setText((String) SPUtils.get(this, "AccountNamePaw3", ""));
        accountName4Edt.setText((String) SPUtils.get(this, "AccountName4", ""));
        accountPassword4Edt.setText((String) SPUtils.get(this, "AccountNamePaw4", ""));
        accountName5Edt.setText((String) SPUtils.get(this, "AccountName5", ""));
        accountPassword5Edt.setText((String) SPUtils.get(this, "AccountNamePaw5", ""));
    }

    private void initSP() {
        SPUtils.put(this, "AccountName1", accountName1Edt.getText().toString());
        SPUtils.put(this, "AccountNamePaw1", accountPassword1Edt.getText().toString());
        SPUtils.put(this, "AccountName2", accountName2Edt.getText().toString());
        SPUtils.put(this, "AccountNamePaw2", accountPassword2Edt.getText().toString());
        SPUtils.put(this, "AccountName3", accountName3Edt.getText().toString());
        SPUtils.put(this, "AccountNamePaw3", accountPassword3Edt.getText().toString());
        SPUtils.put(this, "AccountName4", accountName4Edt.getText().toString());
        SPUtils.put(this, "AccountNamePaw4", accountPassword4Edt.getText().toString());
        SPUtils.put(this, "AccountName5", accountName5Edt.getText().toString());
        SPUtils.put(this, "AccountNamePaw5", accountPassword5Edt.getText().toString());
    }

    @OnClick(R.id.account_btn)
    public void onViewClicked() {
        ToastUtil.showToast(this,"设置成功");
        initSP();
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
