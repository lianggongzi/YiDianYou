package com.duobao.yidianyou.activity.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-07.
 * 输入初始密码进行修改信息页面---暂没用
 */
public class InitializeActivity extends AppCompatActivity {

    @BindView(R.id.initialize_edt)
    ClearWriteEditText initializeEdt;
    @BindView(R.id.initialize_btn)
    Button initializeBtn;

    @BindView(R.id.initialize_tv)
    TextView initializeTv;


    public static Intent newInstance(Context context, String type) {
        Intent intent = new Intent(context, InitializeActivity.class);
        intent.putExtra("type", type);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);
        ButterKnife.bind(this);
        initializeTv.setText(getIntent().getStringExtra("type"));
    }

    @OnClick(R.id.initialize_btn)
    public void onViewClicked() {
        if (initializeEdt.getText().toString().equals("000000")) {
            if (initializeTv.getText().equals("初始化登陆：")) {
                startActivity(new Intent(this, ModifyLoginActivity.class));
                finish();
            } else if (initializeTv.getText().equals("初始化设备：")) {
                startActivity(new Intent(this, ModifyEquipmentActivity.class));
                finish();
            }
        } else {
            ToastUtil.showToast(this, "初始密码不正确");
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
