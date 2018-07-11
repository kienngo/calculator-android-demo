package com.example.admin.calculator.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.calculator.R;
import com.example.admin.calculator.common.Gereral;
import com.example.admin.calculator.model.shared.MySharedPrecence;
import com.example.admin.calculator.presenter.CaculatorPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaculatorFragment extends Fragment implements CaculatorView {
    @BindView(R.id.tv_input)
    TextView tvInput;
    @BindView(R.id.tv_result)
    TextView tvResult;

    private String strInput = "";

    private CaculatorPresenter mCaculatorPresenter;
    private MySharedPrecence mSharedPrecence;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caculator,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        initData();
        super.onStart();
    }

    @Override
    public void resultCaculator(String result) {
        tvResult.setText(result);
        strInput = "";
    }

    @Override
    public void initData() {
        mSharedPrecence = new MySharedPrecence(getActivity());
        mCaculatorPresenter = new CaculatorPresenter(this);

        if (mSharedPrecence.getData(MySharedPrecence.PREF_KEY_RESULT_CACULATOR) == null || mSharedPrecence.getData(MySharedPrecence.PREF_KEY_RESULT_CACULATOR).equals("")){
            tvResult.setText("0");
        }else{
            tvResult.setText(mSharedPrecence.getData(MySharedPrecence.PREF_KEY_RESULT_CACULATOR));
        }
    }

    @OnClick({R.id.btn_ac, R.id.btn_yiya, R.id.btn_percent, R.id.btn_div, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_mul, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_sub, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_sum, R.id.btn_0, R.id.btn_dot, R.id.btn_equalsign})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_ac:
                setClear();
                break;
            case R.id.btn_yiya:
                setTextForInput("-");
                break;
            case R.id.btn_percent:
                setTextForInput("%");
                break;
            case R.id.btn_div:
                setTextForInput("/");
                break;
            case R.id.btn_7:
                setTextForInput("7");
                break;
            case R.id.btn_8:
                setTextForInput("8");
                break;
            case R.id.btn_9:
                setTextForInput("9");
                break;
            case R.id.btn_mul:
                setTextForInput("*");
                break;
            case R.id.btn_4:
                setTextForInput("4");
                break;
            case R.id.btn_5:
                setTextForInput("5");
                break;
            case R.id.btn_6:
                setTextForInput("6");
                break;
            case R.id.btn_sub:
                setTextForInput("-");
                break;
            case R.id.btn_1:
                setTextForInput("1");
                break;
            case R.id.btn_2:
                setTextForInput("2");
                break;
            case R.id.btn_3:
                setTextForInput("3");
                break;
            case R.id.btn_sum:
                setTextForInput("+");
                break;
            case R.id.btn_0:
                setTextForInput("0");
                break;
            case R.id.btn_dot:
                setTextForInput(".");
                break;
            case R.id.btn_equalsign:
                mCaculatorPresenter.transformExpressions(strInput);
                break;
        }
    }

    // set textview when click
    private void setTextForInput(String inp){
        if (inp.equals("")){
            strInput = "";
            tvInput.setText("");
        }else{
            strInput += inp;
            tvInput.setText(strInput);
        }
    }

    // clear data
    public void setClear(){
        setTextForInput("");
        tvInput.setText(Gereral.THREE_DOT);
        tvResult.setText(Gereral.VALUE_O);
    }

    public MySharedPrecence getMySharedPrecence() {
        return mSharedPrecence;
    }
}
