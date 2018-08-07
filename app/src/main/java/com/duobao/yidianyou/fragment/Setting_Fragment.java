package com.duobao.yidianyou.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.activity.Setting.AccountActivity;
import com.duobao.yidianyou.activity.Setting.EquipmentActivity;
import com.duobao.yidianyou.activity.Setting.MoneyActivity;
import com.duobao.yidianyou.activity.Setting.SelectActivity;
import com.duobao.yidianyou.activity.Setting.SwitchAccountActivity;
import com.duobao.yidianyou.utils.ClearWriteEditText;
import com.duobao.yidianyou.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018-07-03.
 */
public class Setting_Fragment extends Fragment {


    @BindView(R.id.setting_switch_ll)
    LinearLayout settingMoneyLl;
    @BindView(R.id.setting_select_ll)
    LinearLayout settingSelectLl;
    @BindView(R.id.setting_account_ll)
    LinearLayout settingAccountLl;
    @BindView(R.id.setting_initialize_ll)
    LinearLayout settingInitializeLl;
    @BindView(R.id.setting_money_ll)
    LinearLayout settingModifyLl;
    Unbinder unbinder;
    String account;
    String password;



    public static Setting_Fragment newInstance() {
        Bundle args = new Bundle();
//        args.putString("name", name);
        Setting_Fragment fragment = new Setting_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        account = (String) SPUtils.get(getActivity(), "account", "");
        password = (String) SPUtils.get(getActivity(), "password", "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.setting_switch_ll, R.id.setting_select_ll, R.id.setting_account_ll, R.id.setting_money_ll, R.id.setting_initialize_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_switch_ll:
                startActivity(new Intent(getActivity(), SwitchAccountActivity.class));//切换收款核销员账号
                break;
            case R.id.setting_money_ll:
                showCustomizeDialog(view.getId());
                break;
            case R.id.setting_select_ll:
                showCustomizeDialog(view.getId());
                break;
            case R.id.setting_account_ll:
                showCustomizeDialog(view.getId());
                break;
            case R.id.setting_initialize_ll:
                showInitializeDialog();
                break;

        }
    }

    //出厂设置Dialog
    private void showInitializeDialog() {
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_setting, null);
        final AlertDialog customizeDialog =
                new AlertDialog.Builder(getActivity())
                        .setTitle("输入初始化密码" )
                        .setView(dialogView)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .setCancelable(false).create();
        customizeDialog.show();
        customizeDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditView中的输入内容,
                ClearWriteEditText edit_text =
                        (ClearWriteEditText) dialogView.findViewById(R.id.dialog_setting);
                if (edit_text.getText().toString().equals("8981")) { //输入的密码与初始化就跳转
                    startActivity(new Intent(getActivity(), EquipmentActivity.class));
                    SPUtils.remove(getActivity(),"equipmentIsLogin");
                    SPUtils.remove(getActivity(),"isLogin");
                    customizeDialog.dismiss();
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "密码不正确", Toast.LENGTH_SHORT).show();
                    edit_text.setShakeAnimation();
                }
            }
        });
        customizeDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeDialog.dismiss();
            }
        });
    }

    private void showCustomizeDialog(final int viewGetID) {
        final View dialogView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_setting, null);
        final AlertDialog customizeDialog =
                new AlertDialog.Builder(getActivity())
                        .setTitle("管理账户：" + account)
                        .setView(dialogView)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .setCancelable(false).create();
        customizeDialog.show();
        customizeDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditView中的输入内容,
                ClearWriteEditText edit_text =
                        (ClearWriteEditText) dialogView.findViewById(R.id.dialog_setting);
                if (edit_text.getText().toString().equals(password)) { //输入的密码与管理员密码吻合就跳转
                    switch (viewGetID) {
                        case R.id.setting_money_ll:
                            startActivity(new Intent(getActivity(), MoneyActivity.class));//设置收款金额
                            break;
                        case R.id.setting_select_ll:
                            startActivity(new Intent(getActivity(), SelectActivity.class));//设置收款类型
                            break;
                        case R.id.setting_account_ll:
                            startActivity(new Intent(getActivity(), AccountActivity.class));//设置收款核销员信息
                            break;
                    }
                    customizeDialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "密码不正确", Toast.LENGTH_SHORT).show();
                    edit_text.setShakeAnimation();
                }
            }
        });
        customizeDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeDialog.dismiss();
            }
        });
    }

}
