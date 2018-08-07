package com.duobao.yidianyou.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.duobao.yidianyou.R;
import com.duobao.yidianyou.base.BaseFragment;
import com.duobao.yidianyou.bean.ReturnBean;
import com.duobao.yidianyou.bean.SweepBean;
import com.duobao.yidianyou.prsenter.SweepPresentImpl;
import com.duobao.yidianyou.utils.DateUtils;
import com.duobao.yidianyou.utils.DesUtils;
import com.duobao.yidianyou.utils.LogUtils;
import com.duobao.yidianyou.utils.MD5Util;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.utils.ToastUtil;
import com.duobao.yidianyou.view.SweepView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.FormBody;


/**
 * Created by Administrator on 2018-07-04.
 */
public class Sweep_Fragment extends BaseFragment<SweepPresentImpl> implements SweepView {
    Unbinder unbinder;
    @BindView(R.id.sweep_successful_logo_iv)
    ImageView sweepSuccessfulLogoIv;
    @BindView(R.id.sweep_successful_cardname_tv)
    TextView sweepSuccessfulCardnameTv;
    @BindView(R.id.sweep_successful_portrait_iv)
    ImageView sweepSuccessfulPortraitIv;
    @BindView(R.id.sweep_successful_name_tv)
    TextView sweepSuccessfulNameTv;
    @BindView(R.id.sweep_successful_region_tv)
    TextView sweepSuccessfulRegionTv;
    @BindView(R.id.sweep_successful_sfz_tv)
    TextView sweepSuccessfulSfzTv;
    @BindView(R.id.sweep_successful_yxq_tv)
    TextView sweepSuccessfulYxqTv;
    @BindView(R.id.sweep_successful_geren_ll)
    RelativeLayout sweepSuccessfulGerenLl;
    @BindView(R.id.sweep_successful_rz_sm_tv)
    TextView sweepSuccessfulRzSmTv;
    @BindView(R.id.sweep_successful_rz_rl_tv)
    TextView sweepSuccessfulRzRlTv;
    @BindView(R.id.sweep_successful_cstatus_tv)
    TextView sweepSuccessfulCstatusTv;
    @BindView(R.id.sweep_successful_rezheng_ll)
    LinearLayout sweepSuccessfulRezhengLl;
    @BindView(R.id.sweep_successful_money_tv)
    TextView sweepSuccessfulMoneyTv;
    @BindView(R.id.sweep_successful_time_tv)
    TextView sweepSuccessfulTimeTv;
    @BindView(R.id.sweep_successful_cardnumber_tv)
    TextView sweepSuccessfulCardnumberTv;
    @BindView(R.id.sweep_successful_ll)
    LinearLayout sweepSuccessfulLl;
    @BindView(R.id.sweep_failure_rl)
    RelativeLayout sweepFailureRl;
    @BindView(R.id.sweep_failure_tv)
    TextView sweepFailureTv;
    @BindView(R.id.sweep_tv)
    TextView sweepTv;
    @BindView(R.id.sweep_account_name_tv)
    TextView sweepAccountNameTv;
    String switchAccount;
    @BindView(R.id.sweep_successful_btn)
    Button sweepSuccessfulBtn;
    @BindView(R.id.sweep_welcome_tv)
    TextView sweepWelcomeTv;

    private ScanBroadcastReceiver scanBroadcastReceiver = null;
    String orderno = "";//订单号
    String cardno = "";//旅游卡号

    public static Sweep_Fragment newInstance() {
        Sweep_Fragment fragment = new Sweep_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sweep, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchAccount = (String) SPUtils.get(getActivity(), "switchAccount", "");
        if (switchAccount.equals("")) {
            sweepAccountNameTv.setTextColor(getResources().getColor(R.color.red));
            sweepAccountNameTv.setText("请设置收款核销员身份");
        } else {
            sweepAccountNameTv.setTextColor(getResources().getColor(R.color.tab_checked));
            sweepAccountNameTv.setText(switchAccount);
            //        注册广播消息
            if (scanBroadcastReceiver == null) {
                scanBroadcastReceiver = new ScanBroadcastReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.zkc.scancode");
                getActivity().registerReceiver(scanBroadcastReceiver, intentFilter);
            }
        }


        //加载网络图片不报错的方法
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
    }

    @OnClick(R.id.sweep_successful_btn)
    public void onViewClicked() {
        pass(orderno, cardno);
    }


    class ScanBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            try {
                orderno = intent.getExtras().getString("code");
                LogUtils.d("feng", orderno);
                sweepTv.setVisibility(View.INVISIBLE);
                params(orderno);
            } catch (Exception e) {
            }
        }
    }


    @Override
    protected void createPresenter() {
        mPresenter = new SweepPresentImpl();
    }

    @Override
    public void getSweep(final SweepBean sweepBean) {
        LogUtils.d("feng", sweepBean.toString());
        if (sweepBean.getCardno() != null) {
            cardno = sweepBean.getCardno();
            sweepWelcomeTv.setVisibility(View.GONE);
            sweepAccountNameTv.setVisibility(View.GONE);
        }
        switch (sweepBean.getStatus()) {
            case 0:
                sweepSuccessfulLl.setVisibility(View.INVISIBLE);
                sweepFailureRl.setVisibility(View.VISIBLE);
                sweepFailureTv.setText(sweepBean.getMsg());
                break;
            case 1:
                sweepSuccessfulLl.setVisibility(View.VISIBLE);
                sweepFailureRl.setVisibility(View.INVISIBLE);
                sweepSuccessfulCardnameTv.setText(sweepBean.getCardname());
                sweepSuccessfulNameTv.setText("姓名：" + sweepBean.getName());
                sweepSuccessfulRegionTv.setText("地区：" + sweepBean.getDq());
//                // 用于显示的加*身份证
//                String show_id = sweepBean.getSfz().substring(0, 3) + "********" + sweepBean.getSfz().substring(11);
                sweepSuccessfulSfzTv.setText("身份证：" + sweepBean.getSfz());
                sweepSuccessfulYxqTv.setText("有效期：" + sweepBean.getYxq());
                if ("1".equals(sweepBean.getRz_sm())) {
                    sweepSuccessfulRzSmTv.setText("已实名认证");
                    Drawable nav_up = getResources().getDrawable(R.drawable.queren1);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    sweepSuccessfulRzSmTv.setCompoundDrawables(null, null, nav_up, null);
                } else {
                    sweepSuccessfulRzSmTv.setText("未实名认证");
                }
                if ("1".equals(sweepBean.getRz_rl())) {
                    sweepSuccessfulRzRlTv.setText("人脸识别");
                } else {
                    sweepSuccessfulRzRlTv.setVisibility(View.GONE);
                }
                if ("1".equals(sweepBean.getCstatus())) {
                    sweepSuccessfulCstatusTv.setText("卡状态：已激活");
                } else {
                    sweepSuccessfulCstatusTv.setText("卡状态：未激活");
                }
                sweepSuccessfulMoneyTv.setText("￥" + sweepBean.getMoney());
                String timeS = DateUtils.getStrTime1(sweepBean.getBuydate());
                sweepSuccessfulTimeTv.setText("购卡日期：" + timeS);
                sweepSuccessfulCardnumberTv.setText("卡号：" + sweepBean.getCardno());
                Glide.with(getActivity())
//                        .load("http://img1.upload.daodb.com/images/ipass/tourVerifyImg/20180703/d639c70a203644e19fb6117fe47bd367.png")
                        .load(sweepBean.getClogo())
                        .centerCrop()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sweepSuccessfulLogoIv);
                Glide.with(getActivity())
                        .load(sweepBean.getRlogo())
                        .centerCrop()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.the_default_avatar)
                        .into(sweepSuccessfulPortraitIv);
                sweepSuccessfulPortraitIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        smallImgClick(sweepBean.getRlogo());
                    }
                });
                break;
        }
    }

    @Override
    public void getPass(ReturnBean returnBean) {
        LogUtils.d("feng", returnBean.toString());
        sweepTv.setVisibility(View.VISIBLE);
        sweepWelcomeTv.setVisibility(View.VISIBLE);
        sweepAccountNameTv.setVisibility(View.VISIBLE);
        sweepSuccessfulLl.setVisibility(View.INVISIBLE);
        sweepFailureRl.setVisibility(View.INVISIBLE);
        switch (returnBean.getStatus()) {
            case 0:
                ToastUtil.showToast(getActivity(), returnBean.getMsg());
                break;
            case 1:
                ToastUtil.showToast(getActivity(), returnBean.getMsg());
                break;
        }
    }


    @Override
    public void getError(String msg) {
        LogUtils.d("feng", msg);
        ToastUtil.showToast(getActivity(), msg);
    }


    //参数加密---.提交旅游卡订单号，更新通行记录
    public void pass(String orderno, String cardno) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("loginnum", SPUtils.get(getActivity(), "equipmentAccount", ""));
            obj.put("pwd", SPUtils.get(getActivity(), "equipmentPassword", ""));
            obj.put("posnum", SPUtils.get(getActivity(), "equipmentJihao", ""));
            obj.put("orderno", orderno);
            obj.put("cardno", cardno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = obj.toString(); // 参数
        String sign = MD5Util.MD5Encode(obj.toString()); // sign  + "ipassduobao@ipass"
        params = DesUtils.desEncrypt("duobaost", params);
        LogUtils.d("feng", params + "---" + sign);
        LogUtils.d("feng", SPUtils.get(getActivity(), "equipmentAccount", "") + "---" + SPUtils.get(getActivity(), "equipmentPassword", "") + "----" + SPUtils.get(getActivity(), "equipmentJihao", ""));
        initPassData(params, sign);
    }

    //网络访问----.提交旅游卡订单号，更新通行记录
    private void initPassData(String parameter, String sign) {
        FormBody formBody = new FormBody
                .Builder()
                .add("parameter", parameter)
                .add("sign", sign)
                .build();
        mPresenter.getPass(formBody);
    }


    //参数加密---.根据订单号查询对应的用户旅游卡详细信息
    public void params(String orderno) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("loginnum", SPUtils.get(getActivity(), "equipmentAccount", ""));
            obj.put("pwd", SPUtils.get(getActivity(), "equipmentPassword", ""));
            obj.put("posnum", SPUtils.get(getActivity(), "equipmentJihao", ""));
            obj.put("orderno", orderno);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = obj.toString(); // 参数
        String sign = MD5Util.MD5Encode(obj.toString()); // sign  + "ipassduobao@ipass"
        params = DesUtils.desEncrypt("duobaost", params);
        LogUtils.d("feng", params + "---" + sign);
        LogUtils.d("feng", SPUtils.get(getActivity(), "equipmentAccount", "") + "---" + SPUtils.get(getActivity(), "equipmentPassword", "") + "----" + SPUtils.get(getActivity(), "equipmentJihao", ""));
        initData(params, sign);
    }

    //网络访问----.根据订单号查询对应的用户旅游卡详细信息
    private void initData(String parameter, String sign) {
        FormBody formBody = new FormBody
                .Builder()
                .add("parameter", parameter)
                .add("sign", sign)
                .build();
        mPresenter.getSweep(formBody);
    }

    public void smallImgClick(String url) {
//        //有背景图
//        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
//        ImageView imgView = getasdView(url);
//        dialog.setView(imgView);
//        dialog.show();

//         全屏显示的方法
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        ImageView imgView = getasdView(url);
        dialog.setContentView(imgView);
        dialog.show();

        // 点击图片消失
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
    }


    public ImageView getasdView(String url) {
        ImageView imgView = new ImageView(getActivity());
        imgView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        imgView.setImageBitmap(returnBitmap(url));
        return imgView;
    }

    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
