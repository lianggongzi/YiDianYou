package com.duobao.yidianyou.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.duobao.yidianyou.R;
import com.duobao.yidianyou.base.BaseFragment;
import com.duobao.yidianyou.bean.CollectionBean;
import com.duobao.yidianyou.prsenter.CollectionPresenterApi;
import com.duobao.yidianyou.prsenter.CollectionPresenterImpl;
import com.duobao.yidianyou.utils.ConvertUtil;
import com.duobao.yidianyou.utils.DesUtils;
import com.duobao.yidianyou.utils.LogUtils;
import com.duobao.yidianyou.utils.MD5Util;
import com.duobao.yidianyou.utils.SPUtils;
import com.duobao.yidianyou.utils.ToastUtil;
import com.duobao.yidianyou.view.CollectionView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.FormBody;

import static java.lang.String.valueOf;

/**
 * Created by Administrator on 2018-07-06.
 */
public class Collection_Fragment extends BaseFragment<CollectionPresenterApi> implements CollectionView {

    private static final String TAG = "Collection_Fragment";
    Unbinder unbinder;
    String Rb1;
    String Rb2;
    String Rb3;
    String Rb4;
    String Rb5;
    @BindView(R.id.collection_choose_rb1)
    RadioButton collectionChooseRb1;
    @BindView(R.id.collection_choose_rb2)
    RadioButton collectionChooseRb2;
    @BindView(R.id.collection_choose_rb3)
    RadioButton collectionChooseRb3;
    @BindView(R.id.collection_choose_rb4)
    RadioButton collectionChooseRb4;
    @BindView(R.id.collection_choose_rb5)
    RadioButton collectionChooseRb5;
    @BindView(R.id.collection_edt)
    EditText collectionEdt;
    @BindView(R.id.collection_choose_btn)
    Button collectionChooseBtn;
    @BindView(R.id.collection_choose_sv)
    ScrollView collectionChooseSv;
    @BindView(R.id.collection_rg)
    RadioGroup collectionRg;
    @BindView(R.id.collection_shuru_money)
    TextView collectionShuruMoney;
    @BindView(R.id.collection_shuru_ll)
    LinearLayout collectionShuruLl;
    @BindView(R.id.collection_money_edt)
    EditText collectionMoneyEdt;

    boolean isSelectType; //根据选择的收款类型来确定布局，默认为true---套餐选择收款类型
    String gatheringName1 = ""; //收款套餐名字
    String gatheringName2 = "";
    String gatheringName3 = "";
    String gatheringName4 = "";
    String gatheringName5 = "";

    String gatheringName1Money;//收款套餐金额
    String gatheringName2Money;
    String gatheringName3Money;
    String gatheringName4Money;
    String gatheringName5Money;

    float money;//单价
    String Amount;//总额
    String remark = "";// 收款类型的描述
    String collectionEdtToString = "";
    String collectionMoneyEdtToString = "";


    private ScanBroadcastReceiverCollection scanBroadcastReceiver = null;

    public static Collection_Fragment newInstance() {
        Collection_Fragment fragment = new Collection_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //注册订阅者
//        EventBus.getDefault().register(this);
        initSP();
        initView();
        //注册接扫描结果收消息广播
        if (scanBroadcastReceiver == null) {
            scanBroadcastReceiver = new ScanBroadcastReceiverCollection();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.zkc.scancode");
            getActivity().registerReceiver(scanBroadcastReceiver, intentFilter);
        }
    }

    private void initSP() {
        isSelectType = (boolean) SPUtils.get(getActivity(), "isSelect", true);
        gatheringName1 = (String) SPUtils.get(getActivity(), "gathering1NameEdt", "");
        gatheringName2 = (String) SPUtils.get(getActivity(), "gathering2NameEdt", "");
        gatheringName3 = (String) SPUtils.get(getActivity(), "gathering3NameEdt", "");
        gatheringName4 = (String) SPUtils.get(getActivity(), "gathering4NameEdt", "");
        gatheringName5 = (String) SPUtils.get(getActivity(), "gathering5NameEdt", "");
        gatheringName1Money = (String) SPUtils.get(getActivity(), "gathering1MoneyEdt", "");
        gatheringName2Money = (String) SPUtils.get(getActivity(), "gathering2MoneyEdt", "");
        gatheringName3Money = (String) SPUtils.get(getActivity(), "gathering3MoneyEdt", "");
        gatheringName4Money = (String) SPUtils.get(getActivity(), "gathering4MoneyEdt", "");
        gatheringName5Money = (String) SPUtils.get(getActivity(), "gathering5MoneyEdt", "");
    }


    private void initView() {
        if (isSelectType == true) {
            collectionRg.setVisibility(View.VISIBLE);
            collectionShuruLl.setVisibility(View.GONE);
            Rb1 = gatheringName1 + gatheringName1Money;
            Rb2 = gatheringName2 + gatheringName2Money;
            Rb3 = gatheringName3 + gatheringName3Money;
            Rb4 = gatheringName4 + gatheringName4Money;
            Rb5 = gatheringName5 + gatheringName5Money;

            if (Rb1.equals("")) {
                collectionChooseRb1.setVisibility(View.GONE);
            } else {
                collectionChooseRb1.setVisibility(View.VISIBLE);
                collectionChooseRb1.setText(Rb1);
            }
            if (Rb2.equals("")) {
                collectionChooseRb2.setVisibility(View.GONE);
            } else {
                collectionChooseRb2.setVisibility(View.VISIBLE);
                collectionChooseRb2.setText(Rb2);
            }
            if (Rb3.equals("")) {
                collectionChooseRb3.setVisibility(View.GONE);
            } else {
                collectionChooseRb3.setVisibility(View.VISIBLE);
                collectionChooseRb3.setText(Rb3);

            }
            if (Rb4.equals("")) {
                collectionChooseRb4.setVisibility(View.GONE);
            } else {
                collectionChooseRb4.setVisibility(View.VISIBLE);
                collectionChooseRb4.setText(Rb4);
            }
            if (Rb5.equals("")) {
                collectionChooseRb5.setVisibility(View.GONE);
            } else {
                collectionChooseRb5.setVisibility(View.VISIBLE);
                collectionChooseRb5.setText(Rb5);
            }

        } else {
            collectionRg.setVisibility(View.GONE);
            collectionShuruLl.setVisibility(View.VISIBLE);
        }

        if (collectionChooseRb1.isChecked() == true) {
            money = ConvertUtil.convertToFloat(gatheringName1Money, 0.00f);
            remark = gatheringName1;
        } else if (collectionChooseRb2.isChecked() == true) {
            money = ConvertUtil.convertToFloat(gatheringName2Money, 0.00f);
            remark = gatheringName2;
        } else if (collectionChooseRb3.isChecked() == true) {
            money = ConvertUtil.convertToFloat(gatheringName3Money, 0.00f);
            remark = gatheringName3;
        } else if (collectionChooseRb4.isChecked() == true) {
            money = ConvertUtil.convertToFloat(gatheringName4Money, 0.00f);
            remark = gatheringName4;
        } else if (collectionChooseRb5.isChecked() == true) {
            money = ConvertUtil.convertToFloat(gatheringName5Money, 0.00f);
            remark = gatheringName5;
        }

        collectionRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.collection_choose_rb1:
                        money = ConvertUtil.convertToFloat(gatheringName1Money, 0.00f);
                        remark = gatheringName1;
                        break;
                    case R.id.collection_choose_rb2:
                        money = ConvertUtil.convertToFloat(gatheringName2Money, 0.00f);
                        remark = gatheringName2;
                        break;
                    case R.id.collection_choose_rb3:
                        money = ConvertUtil.convertToFloat(gatheringName3Money, 0.00f);
                        remark = gatheringName3;
                        break;
                    case R.id.collection_choose_rb4:
                        money = ConvertUtil.convertToFloat(gatheringName4Money, 0.00f);
                        remark = gatheringName4;
                        break;
                    case R.id.collection_choose_rb5:
                        money = ConvertUtil.convertToFloat(gatheringName5Money, 0.00f);
                        remark = gatheringName5;
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.collection_choose_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection_choose_btn:
                collectionEdtToString=collectionEdt.getText().toString();
                collectionMoneyEdtToString=collectionMoneyEdt.getText().toString();
                collectionEdt.clearFocus();//取消焦点
                collectionMoneyEdt.clearFocus();
                //app发送按键广播消息方式
                Intent intentBroadcast = new Intent();
                intentBroadcast.setAction("com.zkc.keycode");
                intentBroadcast.putExtra("keyvalue", 136);
                getActivity().sendBroadcast(intentBroadcast);
        }
    }

    @Override
    protected void createPresenter() {
        mPresenter = new CollectionPresenterImpl();
    }

    @Override
    public void getColltion(CollectionBean collectionBean) {
        if (collectionBean.getMsg() != null) {
            ToastUtil.showToast(getActivity(), collectionBean.getMsg());
        }
    }

    @Override
    public void getError(String msg) {
        if (msg != null) {
            ToastUtil.showToast(getActivity(), msg);
        }
    }

    /**
     * 扫描结果广播
     */
    class ScanBroadcastReceiverCollection extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String decodeResult = intent.getExtras().getString("code");
//            String decodeResult = intent.getExtras().getString("scan");
            String keyStr = "";
            if (decodeResult.contains("{") && decodeResult.contains("}")) {
                int strStart = decodeResult.lastIndexOf("{");
                int strEnd = decodeResult.lastIndexOf("}");
                //check keycode
                if (strStart > -1 && strEnd > -1 && strEnd - strStart < 5) {
                    keyStr = decodeResult.substring(strStart + 1, strEnd);
                    decodeResult = decodeResult.substring(0, strStart);
                }
            }

            try {
                if (isSelectType == true) {
                    Amount = valueOf(money * ConvertUtil.convertToInt(collectionEdtToString, 1));
                    collection(decodeResult, remark, Amount, collectionEdtToString);
                } else if (isSelectType == false) {
                    Amount = valueOf(ConvertUtil.convertToFloat(collectionMoneyEdtToString, 0.00f) * ConvertUtil.convertToInt(collectionEdtToString, 1));
                    collection(decodeResult, "输入性", Amount, collectionEdt.getText().toString());
                }
            } catch (Exception e) {

            }
        }
    }


    //参数加密---.手持机扫码支付
    public void collection(String orderno, String remark, String money, String num) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("loginnum", SPUtils.get(getActivity(), "equipmentAccount", ""));
            obj.put("pwd", SPUtils.get(getActivity(), "equipmentPassword", ""));
            obj.put("posnum", SPUtils.get(getActivity(), "equipmentJihao", ""));
            obj.put("code", orderno);
            obj.put("money", money);
            obj.put("num", num);
            obj.put("remark", remark);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = obj.toString(); // 参数
        String sign = MD5Util.MD5Encode(obj.toString()); // sign  + "ipassduobao@ipass"
        params = DesUtils.desEncrypt("duobaost", params);
        LogUtils.d(TAG, params + "---" + sign);
        LogUtils.d("feng", SPUtils.get(getActivity(), "equipmentAccount", "") + "---" + SPUtils.get(getActivity(), "equipmentPassword", "") + "----" + SPUtils.get(getActivity(), "equipmentJihao", ""));
        initcollectionData(params, sign);
    }

    //网络访问----手持机扫码支付
    private void initcollectionData(String parameter, String sign) {
        FormBody formBody = new FormBody
                .Builder()
                .add("parameter", parameter)
                .add("sign", sign)
                .build();
        mPresenter.getCollectionBoby(formBody);
    }
}
