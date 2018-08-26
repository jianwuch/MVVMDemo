package com.jove.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jove.mvvmdemo.model.data.AccountBean;
import com.jove.mvvmdemo.util.AppLog;
import com.jove.mvvmdemo.viewmodel.AccountModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView activityTextView;
    private Button activityButton;
    private AccountModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityTextView = findViewById(R.id.activity_text);
        activityButton = findViewById(R.id.activity_button);
        activityButton.setOnClickListener(this);
        mModel = ViewModelProviders.of(this).get(AccountModel.class);


        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                AppLog.d(TAG, "onChanged");
                activityTextView.setText(accountBean.toString());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_button:
                mModel.getNetAccount();
                break;
        }
    }
}
