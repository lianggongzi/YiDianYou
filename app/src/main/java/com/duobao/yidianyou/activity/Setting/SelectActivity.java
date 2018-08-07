package com.duobao.yidianyou.activity.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-07-07.
 * 设置收款类型
 */

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.select_rg)
    RadioGroup selectRG;
    @BindView(R.id.select_xunzhe_rb)
    RadioButton selectXunzheRb;
    @BindView(R.id.select_shuru_rb)
    RadioButton selectShuruRb;
    @BindView(R.id.select_btn)
    Button selectBtn;

    boolean isSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if ((boolean)SPUtils.get(this,"isSelect",true)==true){
            selectXunzheRb.setChecked(true);
        }else {
            selectShuruRb.setChecked(true);
        }

        selectRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.select_xunzhe_rb:
                        isSelect=true;
                        SPUtils.put(SelectActivity.this,"isSelect",isSelect);//true为选择收款方式---默认
                        break;
                    case R.id.select_shuru_rb:
                        isSelect=false;
                        SPUtils.put(SelectActivity.this,"isSelect",false);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.select_btn)
    public void onViewClicked() {
        ToastUtil.showToast(this,"设置成功");
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
