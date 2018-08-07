package com.duobao.yidianyou.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.duobao.yidianyou.R;
import com.duobao.yidianyou.fragment.Collection_Fragment;
import com.duobao.yidianyou.fragment.Setting_Fragment;
import com.duobao.yidianyou.fragment.Sweep_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.fragment_demo_ll)
    LinearLayout fragmentDemoLl;
    private Sweep_Fragment sweepFragmenr;
    private Setting_Fragment srttingFragmenr;
    private Collection_Fragment collectionFragment;
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }



    private void initView() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "设置").setActiveColor("#3F51B5"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "扫码").setActiveColor("#3F51B5"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "收款").setActiveColor("#3F51B5"))
                .setFirstSelectedPosition(1)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        sweepFragmenr = Sweep_Fragment.newInstance();
        transaction.replace(R.id.fragment_demo_ll, sweepFragmenr);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (srttingFragmenr == null) {
                    srttingFragmenr = Setting_Fragment.newInstance();
                }
//                showCustomizeDialog(srttingFragmenr, (Integer) SPUtils.get(this, "position", 1));
                //将当前的事务添加到了回退栈
//                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_demo_ll, srttingFragmenr);
                break;
            case 1:
                if (sweepFragmenr == null) {
                    sweepFragmenr = Sweep_Fragment.newInstance();
                }
                transaction.replace(R.id.fragment_demo_ll, sweepFragmenr);
//                SPUtils.put(this, "position", position);
                break;
            case 2:
                if (collectionFragment == null) {
                    collectionFragment = Collection_Fragment.newInstance();
                }
                transaction.replace(R.id.fragment_demo_ll, collectionFragment);
//                SPUtils.put(this, "position", position);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    private void showCustomizeDialog(final Fragment fragment, final int position) {
//        final View dialogView = LayoutInflater.from(this)
//                .inflate(R.layout.dialog_setting, null);
//        final AlertDialog customizeDialog =
//                new AlertDialog.Builder(this)
//                        .setTitle("管理账户：" + account)
//                        .setView(dialogView)
//                        .setNegativeButton("取消", null)
//                        .setPositiveButton("确定", null)
//                        .setCancelable(false).create();
//        customizeDialog.show();
//        customizeDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 获取EditView中的输入内容
//                ClearWriteEditText edit_text =
//                        (ClearWriteEditText) dialogView.findViewById(R.id.dialog_setting);
//                if (edit_text.getText().toString().equals(password)) {
//                    FragmentManager fm = MainActivity.this.getFragmentManager();
//                    //开启事务
//                    FragmentTransaction transaction = fm.beginTransaction();
//                    transaction.replace(R.id.fragment_demo_ll, fragment);
//                    transaction.commit();
//                    customizeDialog.dismiss();
//
//                } else {
//                    Toast.makeText(MainActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
//                    edit_text.setShakeAnimation();
//                }
//            }
//        });
//        TextView forgetTv= (TextView)dialogView. findViewById(R.id.dialog_forget_tv);
//        forgetTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(InitializeActivity.newInstance(MainActivity.this,"初始化登陆："));
//                customizeDialog.dismiss();
//            }
//        });
//        customizeDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomNavigationBar.setFirstSelectedPosition(position)
//                        .initialise();
//                customizeDialog.dismiss();
//            }
//        });
//    }
}
