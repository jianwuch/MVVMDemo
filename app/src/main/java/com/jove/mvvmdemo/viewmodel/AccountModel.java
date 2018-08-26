package com.jove.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.jove.mvvmdemo.model.data.AccountBean;
import com.jove.mvvmdemo.util.AppLog;

/**
 * author : jove.chen
 * e-mail : jianwuch@qq.com
 * date   : 18-8-23下午4:13
 * desc   :
 * version: 1.0
 */
public class AccountModel extends AndroidViewModel {
    private static final String TAG = AccountModel.class.getSimpleName();
    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();

    public AccountModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<AccountBean> getAccount() {
        return mAccount;
    }

    public void setAccount(AccountBean mAccount) {
        this.mAccount.setValue(mAccount);
    }

    /**
     * 获取数据
     */
    public void getNetAccount() {
        AccountBean accountBean = new AccountBean();
        accountBean.id = 12345;
        accountBean.name = "jove.chen";
        accountBean.gender = "男";
        setAccount(accountBean);
    }

    //销毁时调用
    @Override
    protected void onCleared() {
        super.onCleared();
        AppLog.d(TAG, "onCleared");
    }
}
