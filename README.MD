# ViewModel
ViewModel类的设计目的是以一种关注生命周期的方式存储和管理与UI相关的数据。 
# LiveData
LiveData是一个可观察的数据持有者类。与常见的观察者不同，LiveData是有生命周期感知的。这意味着它尊重其他应用程序组件的生命周期，比如Activity、Fragment或Service。这种感知确保LiveData只更新处于生命周期状态内的应用程序组件。
# 使用方法
## 定义ViewModel
```java
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
```
## 使用
```java
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
```

# 使用场景延伸
Activity和Fragment, Fragment和Fragment之间进行数据共享
在Fragment中使用方法和Activity中一致
## Fragment中使用
```java
public class BottomFragment extends Fragment {

    private AccountModel mModel;
    private TextView mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = view.findViewById(R.id.fragment_text_view);
        mModel = ViewModelProviders.of(getActivity()).get(AccountModel.class);
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getPhone(), accountBean.getBlog()));
            }
        });
    }
}
```