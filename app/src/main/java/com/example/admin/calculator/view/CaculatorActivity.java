package com.example.admin.calculator.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.example.admin.calculator.R;
import com.example.admin.calculator.model.shared.MySharedPrecence;

import butterknife.ButterKnife;

public class CaculatorActivity extends AppCompatActivity{
    private CaculatorFragment mCaculatorFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator);
        ButterKnife.bind(this);

        initFragment();
    }

    public void initFragment() {
        mCaculatorFragment = new CaculatorFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, mCaculatorFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_clear:
                mCaculatorFragment.setClear();
                break;
            case R.id.action_save:
                mCaculatorFragment.getMySharedPrecence().setData(MySharedPrecence.PREF_KEY_RESULT_CACULATOR,mCaculatorFragment.tvResult.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
