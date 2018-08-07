package com.duobao.yidianyou.activity.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-07.
 * 设置收款
 */

public class MoneyActivity extends AppCompatActivity {

    @BindView(R.id.gathering1_name_edt)
    EditText gathering1NameEdt;
    @BindView(R.id.gathering1_money_edt)
    EditText gathering1MoneyEdt;
    @BindView(R.id.gathering2_name_edt)
    EditText gathering2NameEdt;
    @BindView(R.id.gathering2_money_edt)
    EditText gathering2MoneyEdt;
    @BindView(R.id.gathering3_name_edt)
    EditText gathering3NameEdt;
    @BindView(R.id.gathering3_money_edt)
    EditText gathering3MoneyEdt;
    @BindView(R.id.gathering4_name_edt)
    EditText gathering4NameEdt;
    @BindView(R.id.gathering4_money_edt)
    EditText gathering4MoneyEdt;
    @BindView(R.id.gathering5_name_edt)
    EditText gathering5NameEdt;
    @BindView(R.id.gathering5_money_edt)
    EditText gathering5MoneyEdt;
    @BindView(R.id.collection_money_renshu_btn)
    Button collectionMoneyRenshuBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeny);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        gathering1NameEdt.setText((String) SPUtils.get(this, "gathering1NameEdt", ""));
        gathering1NameEdt.setSelection(((String) SPUtils.get(this, "gathering1NameEdt", "")).length());
        gathering1MoneyEdt.setText((String) SPUtils.get(this, "gathering1MoneyEdt", ""));
        gathering2NameEdt.setText((String) SPUtils.get(this, "gathering2NameEdt", ""));
        gathering2MoneyEdt.setText((String) SPUtils.get(this, "gathering2MoneyEdt", ""));
        gathering3NameEdt.setText((String) SPUtils.get(this, "gathering3NameEdt", ""));
        gathering3MoneyEdt.setText((String) SPUtils.get(this, "gathering3MoneyEdt", ""));
        gathering4NameEdt.setText((String) SPUtils.get(this, "gathering4NameEdt", ""));
        gathering4MoneyEdt.setText((String) SPUtils.get(this, "gathering4MoneyEdt", ""));
        gathering5NameEdt.setText((String) SPUtils.get(this, "gathering5NameEdt", ""));
        gathering5MoneyEdt.setText((String) SPUtils.get(this, "gathering5MoneyEdt", ""));
    }

    private void initSP() {
        SPUtils.put(this, "gathering1NameEdt", gathering1NameEdt.getText().toString());
        SPUtils.put(this, "gathering1MoneyEdt", gathering1MoneyEdt.getText().toString());
        SPUtils.put(this, "gathering2NameEdt", gathering2NameEdt.getText().toString());
        SPUtils.put(this, "gathering2MoneyEdt", gathering2MoneyEdt.getText().toString());
        SPUtils.put(this, "gathering3NameEdt", gathering3NameEdt.getText().toString());
        SPUtils.put(this, "gathering3MoneyEdt", gathering3MoneyEdt.getText().toString());
        SPUtils.put(this, "gathering4NameEdt", gathering4NameEdt.getText().toString());
        SPUtils.put(this, "gathering4MoneyEdt", gathering4MoneyEdt.getText().toString());
        SPUtils.put(this, "gathering5NameEdt", gathering5NameEdt.getText().toString());
        SPUtils.put(this, "gathering5MoneyEdt", gathering5MoneyEdt.getText().toString());
    }

    @OnClick({R.id.collection_money_renshu_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection_money_renshu_btn:
                initSP();
                finish();
                break;
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
